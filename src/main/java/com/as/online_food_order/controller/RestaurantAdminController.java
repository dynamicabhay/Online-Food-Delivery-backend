package com.as.online_food_order.controller;

import com.as.online_food_order.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant-admin")
public class RestaurantAdminController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('RESTAURANT_OWNER')")
    public String test(){
        return "hello from restaurant admin !!";
    }

}
