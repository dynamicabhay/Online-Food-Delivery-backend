package com.as.online_food_order.model.repositories;

import com.as.online_food_order.model.Role;
import com.as.online_food_order.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleEnum name);
}
