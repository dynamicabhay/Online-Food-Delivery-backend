package com.as.online_food_order.repositories;

import com.as.online_food_order.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
