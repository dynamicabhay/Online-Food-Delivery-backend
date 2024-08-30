package com.as.online_food_order.Utils;

import com.as.online_food_order.model.Role;
import com.as.online_food_order.model.RoleEnum;
import com.as.online_food_order.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Order(1)
public class RoleSeeder implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepo;

    @Override
    public void run(String... args) throws Exception {
        List<RoleEnum> roles = Arrays.asList(RoleEnum.ADMIN,RoleEnum.USER,RoleEnum.RESTAURANT_OWNER);

        Map<RoleEnum,String> map = Map.of(
             RoleEnum.ADMIN , "Administrator Role",
             RoleEnum.USER , "User Role",
             RoleEnum.RESTAURANT_OWNER , "Restaurant Owner Role"
        );

        roles.forEach(role -> {
            Optional<Role> db_role = roleRepo.findByName(role);
            db_role.ifPresentOrElse(System.out::println,() -> {
                Role newRole = Role.builder().name(role).description(map.get(role)).build();
                roleRepo.save(newRole);
            });
        });
    }
}
