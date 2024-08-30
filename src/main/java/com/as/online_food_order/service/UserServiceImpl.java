package com.as.online_food_order.service;

import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.model.User;
import com.as.online_food_order.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository,JwtService jwtService,ModelMapper mapper){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.mapper = mapper;
    }
    @Override
    public UserDTO getUserProfile(String token) {
        String userEmail = jwtService.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        UserDTO res = mapper.map(user,UserDTO.class);
        return res;
    }

    @Override
    public User getUserFromJwt(String token) {
        String userEmail = jwtService.extractUsername(token.substring(7));
        return userRepository.findByEmail(userEmail).orElseThrow();
    }
}
