package com.lcwd.user.service.UserService.controllers;

import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.GlobalException;
import com.lcwd.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-service")
public class UserController {
    @Autowired
    private UserService userService;

    Logger logger=LoggerFactory.getLogger(UserController.class);

    @PostMapping("/new-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    int retryCounter=1;

    @GetMapping("/{userId}")
    //@CircuitBreaker(name="hotelRatingsBreaker", fallbackMethod = "hotelRatingsFallback")
    //@Retry(name="hotelRatingsBreaker", fallbackMethod = "hotelRatingsFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "hotelRatingFallback")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        logger.info("Retry Counter:{}",retryCounter);
        retryCounter++;
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    public ResponseEntity<User> hotelRatingsFallback(Long userId,Exception e){
        logger.info("Fallback called, Service is down!"+e.getMessage());

        User user=new User();
        user.setUserName("Dummy");
        user.setUserEmail("dummy@gmail.com");
        user.setUserInfo("Dummy created cz some service is down!");

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId,@RequestBody User user){
        if(userService.updateUserById(userId,user)){
            return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
        }
        throw new GlobalException("Some Error occurred!");
    }

    @PostMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        if(userService.deleteUserById(userId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new GlobalException("Some Error occurred!");
    }

}