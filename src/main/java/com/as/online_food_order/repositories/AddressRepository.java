package com.as.online_food_order.repositories;

import com.as.online_food_order.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
