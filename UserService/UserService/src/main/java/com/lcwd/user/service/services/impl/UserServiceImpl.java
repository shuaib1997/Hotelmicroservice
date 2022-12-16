package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

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
        ArrayList<Rating> ratingOfUser= this.restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
        logger.info("{}",ratingOfUser);
        user.setRatings(ratingOfUser);
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
