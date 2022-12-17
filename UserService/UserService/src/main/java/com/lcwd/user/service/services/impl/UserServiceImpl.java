package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Uesr with given id is not found on server"+userId));
        //fetch rating of the above user from RATING SERVICE
        //http://localhost:8083/ratings/users/3b4c1887-41a0-47c7-abf1-0fd40ca52c85
        Rating[] ratingOfUser= this.restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);

        List<Rating> ratings=Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList= ratings.stream().map(rating->{
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/73fe9548-bc0c-44b3-a390-81e0be146e92
           // ResponseEntity<Hotel> forEntity=this.restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            Hotel hotel=this.hotelService.getHotel(rating.getHotelId());
          //  logger.info("response status code: {}",forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {

        return null;
    }
}
