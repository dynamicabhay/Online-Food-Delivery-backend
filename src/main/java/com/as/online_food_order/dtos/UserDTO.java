package com.as.online_food_order.dtos;

import com.as.online_food_order.model.Address;
import com.as.online_food_order.model.Order;
import com.as.online_food_order.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private Long userId;
    private String fullName;
    private String email;
    private Role role;
    private List<Order> orders = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private boolean status;

}
