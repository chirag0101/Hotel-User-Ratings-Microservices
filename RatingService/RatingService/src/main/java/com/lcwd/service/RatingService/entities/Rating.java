package com.lcwd.service.RatingService.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document("ratings")
public class Rating {
    @Id
    private String ratingId;

    private Long userId;

    private Long hotelId;

    private int rating;

    private String feedback;

    public Rating(){}

    public Rating(String ratingId, Long userId, Long hotelId, int rating, String feedback) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.feedback = feedback;
    }

}