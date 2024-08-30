package com.as.online_food_order.Utils;

import com.as.online_food_order.dtos.SignUpRequest;
import com.as.online_food_order.model.Role;
import com.as.online_food_order.model.RoleEnum;
import com.as.online_food_order.model.User;
import com.as.online_food_order.repositories.RoleRepository;
import com.as.online_food_order.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2)
@Slf4j
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        SignUpRequest user = SignUpRequest.builder()
                .email("admin@gmail.com")
                .fullName("admin")
                .password("admin")
                .build();
        Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
        Optional<Role> optionalRole = roleRepo.findByName(RoleEnum.ADMIN);

        if(optionalRole.isEmpty())
            throw new RuntimeException(("please Create ADMIN role in db first"));

        if(optionalUser.isEmpty()){
            User newUser = User.builder()
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(optionalRole.get())
                    .status(true)
                    .build();
            userRepo.save(newUser);
            log.info("admin user is created");
        }
        else log.info("admin user already exists in db");
    }
}
