package com.as.online_food_order.controller;

import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.repositories.UserRepository;
import com.as.online_food_order.service.JwtService;
import com.as.online_food_order.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

   private final UserService userService;

   public UserController(UserService userService){
       this.userService = userService;
   }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test(){
        return "hello from user";
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(@RequestHeader("Authorization") String jwt){

        return new ResponseEntity<>(userService.getUserProfile(jwt), HttpStatus.ACCEPTED);

    }
}
