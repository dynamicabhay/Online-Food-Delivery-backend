package com.as.online_food_order.repositories;


import com.as.online_food_order.dtos.RestaurantReqResDTO;
import com.as.online_food_order.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    @Query("select r from Restaurant r where lower(r.name) like %:query% or lower(r.cuisineType) like %:query%")
    List<Restaurant> searchByRestaurantNameOrCuisineType(String query);


}
