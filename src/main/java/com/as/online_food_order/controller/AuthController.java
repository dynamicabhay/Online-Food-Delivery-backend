package com.as.online_food_order.controller;

import com.as.online_food_order.dtos.SignInRequest;
import com.as.online_food_order.dtos.SignInResponse;
import com.as.online_food_order.dtos.SignUpRequest;
import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.model.RoleEnum;
import com.as.online_food_order.model.User;
import com.as.online_food_order.service.AuthenticationService;
import com.as.online_food_order.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authService;
    private final JwtService jwtService;
    public AuthController(AuthenticationService authService,JwtService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequest request){
        return new ResponseEntity<>(authService.signup(request,true), HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request){

       User user  = authService.authenticate(request);
       String token = jwtService.generateToken(user);

       SignInResponse res = SignInResponse.builder()
               .token(token)
               .email(user.getEmail())
               .fullName(user.getFullName())
               .roleAssigned(user.getRole().getName().name())
               .expiresIn(jwtService.getExpirationTime())
               .userId(user.getId())
               .build();
       return new ResponseEntity<>(res,HttpStatus.CREATED);

    }
}
