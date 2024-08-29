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
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String  name;

    @ManyToOne
    private IngredientCategory category;

    @ManyToMany
    @JoinTable(
            name = "restaurant_ingredientItems",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_item_id")
    )
    private List<Restaurant> restaurants = new ArrayList<>();

    private boolean inStock;
}

