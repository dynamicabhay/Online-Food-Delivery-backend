package com.as.online_food_order.dtos;

import com.as.online_food_order.model.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReqResDTO {

    private Long id;

    private String name;

    private String description;

    private String cuisineType;

    private List<Address> address = new ArrayList<>();

    private ContactInformation contactInformation;

    private String openingHours;

    private List<String> images = new ArrayList<>();

    private boolean open;



}
