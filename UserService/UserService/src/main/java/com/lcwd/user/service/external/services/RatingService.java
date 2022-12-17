package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating values);

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating>  updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/rating/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
