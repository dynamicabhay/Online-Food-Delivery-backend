package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @ManyToMany(mappedBy = "foodCategories")
   private List<Restaurant> restaurants = new ArrayList<>();
}
