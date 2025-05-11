package com.lcwd.service.RatingService.services;

import com.lcwd.service.RatingService.entities.Rating;
import com.lcwd.service.RatingService.repositories.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    public void createRating(Rating rating){
        ratingRepo.save(rating);
    }

    public List<Rating> getRatings(){
        return ratingRepo.findAll();
    }

    public List<Rating> getRatingsByUserId(Long userId){
        return ratingRepo.findByUserId(userId);
    }

    public List<Rating> getRatingsByHotelId(Long userId){
        return ratingRepo.findByHotelId(userId);
    }
}