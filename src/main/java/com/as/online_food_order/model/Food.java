package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private float price;

    @ManyToOne
    private FoodCategory foodCategory;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private boolean available;

    @ManyToMany(mappedBy = "foods")
    private List<Restaurant> restaurants = new ArrayList<>();

    private boolean isVegetarian;

    private boolean isSeasonal;

    @OneToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();

    @CreationTimestamp
    private Date creationDate;
}
