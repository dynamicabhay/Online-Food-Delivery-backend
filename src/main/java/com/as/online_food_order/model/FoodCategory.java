package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   private String  name;

    @ManyToMany
    @JoinTable(name = "restaurant_foodCategory",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "foodCategory_id")
    )
   private List<Restaurant> restaurants;
}
