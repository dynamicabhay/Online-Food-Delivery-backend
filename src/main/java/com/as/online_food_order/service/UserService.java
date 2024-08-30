package com.as.online_food_order.service;

import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.model.User;

public interface UserService {
    UserDTO getUserProfile(String token);
    User getUserFromJwt(String token);
}
