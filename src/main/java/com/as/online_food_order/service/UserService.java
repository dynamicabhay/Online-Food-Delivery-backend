package com.as.online_food_order.service;

import com.as.online_food_order.dtos.UserDTO;

public interface UserService {
    UserDTO getUserProfile(String token);
}
