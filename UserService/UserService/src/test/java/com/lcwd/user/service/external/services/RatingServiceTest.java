package com.lcwd.user.service.external.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RatingServiceTest {

    @Autowired
    private RatingService ratingService;
   /* @Test
    void createRating() {
        Rating rating=Rating.builder().ratingId("jbdkjsgdiu").feedback("this is good hotel to stay!").rating(8).build();
        ResponseEntity<Rating> createdRating=this.ratingService.createRating(rating);
        System.out.println("rating has been created!"+createdRating.getBody()+createdRating.getStatusCode()+createdRating.getHeaders());
    }*/
}