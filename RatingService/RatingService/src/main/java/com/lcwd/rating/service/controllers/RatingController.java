package com.lcwd.rating.service.controllers;

import com.lcwd.rating.service.entities.Rating;
import com.lcwd.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
    }

    @GetMapping("")
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(this.ratingService.getAllRating());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(this.ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(this.ratingService.getRatingByHotelId(hotelId));
    }

}
