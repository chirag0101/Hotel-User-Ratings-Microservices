package com.lcwd.user.service.UserService.entities;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private Object ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feed;
    private Hotel hotel;
}