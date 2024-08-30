package com.as.online_food_order.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponse {
    private String fullName;
    private Long userId;
    private boolean status;
    private String roleAssigned;
    private String email;
    private String token;
    private long expiresIn;
}
