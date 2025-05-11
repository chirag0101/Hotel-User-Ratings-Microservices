package com.hotel.service.HotelService.services;

import com.hotel.service.HotelService.entities.Hotel;
import com.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.service.HotelService.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    public void createHotel(Hotel hotel){
        hotelRepo.save(hotel);
    }

    public List<Hotel> allHotels(){
        return hotelRepo.findAll();
    }

    public Hotel getHotel(Long id){
        return hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel Not Found!"));
    }
}