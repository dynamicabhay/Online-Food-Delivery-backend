package com.as.online_food_order.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
