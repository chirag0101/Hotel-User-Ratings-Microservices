package com.lcwd.service.RatingService.repositories;

import com.lcwd.service.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends MongoRepository<Rating,String> {
    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(Long hotelId);
}