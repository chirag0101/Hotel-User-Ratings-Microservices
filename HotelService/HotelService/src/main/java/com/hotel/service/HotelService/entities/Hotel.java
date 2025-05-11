package com.hotel.service.HotelService.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_HOTEL")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_ID")
    private Long hotelId;

    @Column(name = "HOTEL_NAME")
    private String hotelName;

    @Column(name = "HOTEL_LOCATION")
    private String location;

    @Column(name = "HOTEL_ABOUT")
    private String about;

    public Hotel(){}

    public Hotel(Long hotelId, String hotelName, String location, String about) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.about = about;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}