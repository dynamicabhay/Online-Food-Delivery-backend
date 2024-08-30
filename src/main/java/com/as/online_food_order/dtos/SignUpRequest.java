package com.as.online_food_order.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private String email;

    private String password;

    private String fullName;

}
