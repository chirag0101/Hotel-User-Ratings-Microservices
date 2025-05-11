package com.hotel.service.HotelService.controllers;

import com.hotel.service.HotelService.entities.Hotel;
import com.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-service")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Long hotelId){
        return new ResponseEntity<>(hotelService.getHotel(hotelId), HttpStatus.OK);
    }

    @GetMapping("/all-hotels")
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return new ResponseEntity<>(hotelService.allHotels(),HttpStatus.OK);
    }

    @PostMapping("/new-hotel")
    public ResponseEntity<?> addHotel(@RequestBody Hotel hotel){
        hotelService.createHotel(hotel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}