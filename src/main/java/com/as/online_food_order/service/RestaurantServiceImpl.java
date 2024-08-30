package com.as.online_food_order.service;

import com.as.online_food_order.dtos.RestaurantReqResDTO;
import com.as.online_food_order.dtos.UserDTO;
import com.as.online_food_order.model.Address;
import com.as.online_food_order.model.Restaurant;
import com.as.online_food_order.model.User;
import com.as.online_food_order.repositories.AddressRepository;
import com.as.online_food_order.repositories.RestaurantRepository;
import com.as.online_food_order.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final AddressRepository addressRepository;
    public RestaurantServiceImpl(
            RestaurantRepository restaurantRepository,
            UserService userService,
            ModelMapper mapper,
            AddressRepository addressRepository){
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
    }
    @Override
    public RestaurantReqResDTO createRestaurant(RestaurantReqResDTO request, User user) {
       List<Address> savedAddress =  addressRepository.saveAll(request.getAddress());
        Restaurant newRestaurant = Restaurant.builder()
                .name(request.getName())
                .open(true)
                .images(request.getImages())
                .cuisineType(request.getCuisineType())
                .description(request.getDescription())
                .contactInformation(request.getContactInformation())
                .openingHours(request.getOpeningHours())
                .build();
        if(newRestaurant.getAddress() == null){
            newRestaurant.setAddress(new ArrayList<>());
        }
        newRestaurant.getAddress().addAll(savedAddress);
        newRestaurant.setOwner(user);
        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        RestaurantReqResDTO res = mapper.map(savedRestaurant,RestaurantReqResDTO.class);

        return res;

    }

    @Override
    public List<RestaurantReqResDTO> getAllRestaurant() {
        List<RestaurantReqResDTO> res = new ArrayList<>();

       List<Restaurant> list = restaurantRepository.findAll();

      return list.stream().map(restaurant -> {
           return mapper.map(restaurant, RestaurantReqResDTO.class);
       }).collect(Collectors.toList());

    }

    @Override
    public RestaurantReqResDTO getRestaurantById(Long id) throws Exception {
       Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no restaurant is present for id: " + id));
       return mapper.map(restaurant,RestaurantReqResDTO.class);
    }

    @Override
    public RestaurantReqResDTO updateRestaurant(Long id, RestaurantReqResDTO request) throws Exception {
        return null;
    }

    @Override
    public List<RestaurantReqResDTO> searchRestaurant(String queryString) {
        List<Restaurant> res = restaurantRepository.searchByRestaurantNameOrCuisineType(queryString);
        return res.stream().map(restaurant ->{
            return mapper.map(restaurant, RestaurantReqResDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO getOwnerByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no restaurant is present for id: " + id));
        User user = restaurant.getOwner();
        return mapper.map(user,UserDTO.class);

    }

    @Override
    public String deleteRestaurantById(Long id) throws Exception {
        restaurantRepository.deleteById(id);
        return "restaurant with id: " + id + " deleted successfully !";
    }

    @Override
    public RestaurantReqResDTO toggleRestaurantState(Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no restaurant is present for id: " + id));
        restaurant.setOpen(!restaurant.isOpen());
        Restaurant savedRest = restaurantRepository.save(restaurant);
        return mapper.map(savedRest,RestaurantReqResDTO.class);

    }


}
