package com.as.online_food_order.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDTO {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;

    private List<String> images;
}
