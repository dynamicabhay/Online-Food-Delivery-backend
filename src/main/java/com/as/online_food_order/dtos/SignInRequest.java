package com.as.online_food_order.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequest {
    private String email;

    private String password;
}
