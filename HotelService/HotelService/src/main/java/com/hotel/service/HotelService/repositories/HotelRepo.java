package com.hotel.service.HotelService.repositories;

import com.hotel.service.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,Long> {
}