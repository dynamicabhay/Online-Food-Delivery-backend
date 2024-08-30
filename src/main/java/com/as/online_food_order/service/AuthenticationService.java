package com.as.online_food_order.service;

import com.as.online_food_order.dtos.SignInRequest;
import com.as.online_food_order.dtos.SignUpRequest;
import com.as.online_food_order.dtos.SignUpResponse;
import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.exceptions.UserAlreadyExistsException;
import com.as.online_food_order.model.Cart;
import com.as.online_food_order.model.Role;
import com.as.online_food_order.model.RoleEnum;
import com.as.online_food_order.model.User;
import com.as.online_food_order.repositories.CartRepository;
import com.as.online_food_order.repositories.RoleRepository;
import com.as.online_food_order.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CartRepository cartRepository;

    private final ModelMapper mapper;

    private final RoleRepository roleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            CartRepository cartRepository,
            ModelMapper mapper,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
    }

    public UserDTO signup(SignUpRequest request,boolean isUser) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        Optional<Role> optionalRole = (isUser) ? roleRepository.findByName(RoleEnum.USER) : roleRepository.findByName(RoleEnum.RESTAURANT_OWNER);
        if(existingUser.isPresent())
            throw new UserAlreadyExistsException(request.getFullName() + " already present " + " with email: " + request.getEmail());
        if(optionalRole.isEmpty())
            throw new RuntimeException("no ROLE_USER is present in db");
        // creating new User role

        // creating new user to register into db
        User newUser = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(optionalRole.get())
                .status(true)
                .build();
        User db_user = userRepository.save(newUser);
        // creating cart for that new user
        if(isUser) {
            Cart newCart = Cart.builder().total(0).customer(db_user).build();
            cartRepository.save(newCart);
        }

      UserDTO res = mapper.map(db_user,UserDTO.class);

        return res;
    }

    public User authenticate(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return userRepository.findByEmail(request.getEmail())
                .orElseThrow();

    }



}
