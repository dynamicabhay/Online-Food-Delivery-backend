package com.as.online_food_order.controller;

import com.as.online_food_order.dtos.SignInRequest;
import com.as.online_food_order.dtos.SignInResponse;
import com.as.online_food_order.dtos.SignUpRequest;
import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthenticationService authService;

    public AdminController(AuthenticationService authService){
        this.authService = authService;
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String test(){
        return "hello from admin";
    }



    @PostMapping("create-restaurant-owner")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO>  signUp(@RequestBody SignUpRequest request){

        return new ResponseEntity<>(authService.signup(request,false), HttpStatus.CREATED);
    }
}
