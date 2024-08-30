package com.as.online_food_order.controller;

import com.as.online_food_order.dtos.RestaurantReqResDTO;
import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.model.User;
import com.as.online_food_order.service.RestaurantService;
import com.as.online_food_order.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;
    public RestaurantController(RestaurantService restaurantService, UserService userService){
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('RESTAURANT_OWNER')")
    public ResponseEntity<RestaurantReqResDTO> createRestaurant(
            @RequestBody RestaurantReqResDTO request,
            @RequestHeader("Authorization") String token){
        User user = userService.getUserFromJwt(token);
        return new ResponseEntity<>(restaurantService.createRestaurant(request,user), HttpStatus.CREATED);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    public ResponseEntity<List<RestaurantReqResDTO>> getAllRestaurant(){
        return new ResponseEntity<>(restaurantService.getAllRestaurant(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantReqResDTO> getRestaurantById(@PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(restaurantService.getRestaurantById(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantReqResDTO>> searchRestaurant(@RequestParam(name ="query") String query){
        System.out.println(query);
        return new ResponseEntity<>(restaurantService.searchRestaurant(query),HttpStatus.ACCEPTED);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<UserDTO> findOwnerByRestaurantId(@PathVariable(name ="id") Long id) throws Exception{

      return new ResponseEntity<>(restaurantService.getOwnerByRestaurantId(id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurantById(@PathVariable(name = "id") Long id) throws Exception{
        return new ResponseEntity<>(restaurantService.deleteRestaurantById(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/toggle/{id}")
    public ResponseEntity<RestaurantReqResDTO> toggleRestaurantState(@PathVariable Long id) throws Exception{
        return new ResponseEntity<>(restaurantService.toggleRestaurantState(id),HttpStatus.CREATED);
    }

}
