package com.lcwd.user.service.UserService.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private Long hotelId;
    private String hotelName;
    private String location;
    private String about;
}