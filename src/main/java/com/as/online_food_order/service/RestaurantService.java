package com.as.online_food_order.service;

import com.as.online_food_order.dtos.RestaurantReqResDTO;
import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.model.User;

import java.util.List;

public interface RestaurantService {

    RestaurantReqResDTO createRestaurant(RestaurantReqResDTO request, User usesr);

    List<RestaurantReqResDTO>  getAllRestaurant();

    RestaurantReqResDTO getRestaurantById(Long id) throws Exception;

    RestaurantReqResDTO updateRestaurant(Long id, RestaurantReqResDTO request) throws Exception;

    List<RestaurantReqResDTO> searchRestaurant(String queryString);

    UserDTO getOwnerByRestaurantId(Long id) throws Exception;

    String deleteRestaurantById(Long id) throws Exception;

    RestaurantReqResDTO toggleRestaurantState(Long id) throws Exception;


}
