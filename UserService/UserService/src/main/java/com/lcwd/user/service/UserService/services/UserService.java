package com.lcwd.user.service.UserService.services;

import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.external.services.HotelService;
import com.lcwd.user.service.UserService.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    public User saveUser(User user){
//        user.setUserId((Long.parseLong(UUID.randomUUID().toString())));
        userRepo.saveAndFlush(user);
        return userRepo.findById(user.getUserId()).get();
    }

    public List<User> getAllUsers(){
        List<User> users=userRepo.findAll();
        for(User u:users){
            u.setRatings(restTemplate.getForObject("http://RATING-SERVICE/api/ratings/user/"+u.getUserId(),ArrayList.class));
        }
        return users;
    }

    public User getUserById(Long id){
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found!"));
        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/api/ratings/user/"+id,Rating[].class);
//        user.setRatings(ratingsOfUser);

        assert ratingsOfUser != null;
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingsList=ratings.stream().map(rating -> {
            //Hotel hotel=restTemplate.getForObject("http://HOTEL-SERVICE/api/hotel/"+rating.getHotelId(),Hotel.class);
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).toList();

        user.setRatings(ratingsList);

        return user;
    }

    public Boolean deleteUserById(Long id){
        User userInDb=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found!"));

        userRepo.deleteById(id);

        return true;
    }

    public Boolean updateUserById(Long id,User user){
        User userInDb=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found!"));

        userInDb.setUserName(user.getUserName());
        userInDb.setUserEmail(user.getUserEmail());
        userInDb.setUserInfo(user.getUserInfo());

        userRepo.save(userInDb);
        return true;
    }

}