package com.lcwd.service.RatingService.controllers;

import com.lcwd.service.RatingService.entities.Rating;
import com.lcwd.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating-service")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/get-all-ratings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        return new ResponseEntity<>(ratingService.getRatings(),HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotel(@PathVariable Long hotelId){
        return new ResponseEntity<>(ratingService.getRatingsByHotelId(hotelId),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(ratingService.getRatingsByUserId(userId),HttpStatus.OK);
    }

    @PostMapping("/new-rating")
    public ResponseEntity<?> newRating(@RequestBody Rating rating){
        ratingService.createRating(rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}