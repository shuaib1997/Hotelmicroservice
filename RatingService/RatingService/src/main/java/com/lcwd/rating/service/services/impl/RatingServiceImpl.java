package com.lcwd.rating.service.services.impl;

import com.lcwd.rating.service.entities.Rating;
import com.lcwd.rating.service.repositories.RatingRepository;
import com.lcwd.rating.service.services.RatingService;
import com.lcwd.rating.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        String randomId=UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }


}
