package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    @OneToOne
   private User owner;
   private String name;
   private String description;
   private String cuisineType;

   @OneToMany
   private List<Address> address;

   @Embedded
   private ContactInformation contactInformation;

   private String openingHours;
   private String reviews;

   @OneToMany(mappedBy = "restaurant")
   private List<Order> orders = new ArrayList<>();

   private int numRating;

   @ElementCollection
   @Column(name = "url")
   private List<String> images = new ArrayList<>();

   @CreationTimestamp
   private Date registrationDate;

   @ColumnDefault("true")
   private boolean open;

   @ManyToMany
   @JoinTable(name = "restaurant_food",
           joinColumns = @JoinColumn(name = "restaurant_id"),
           inverseJoinColumns = @JoinColumn(name = "food_id")
   )
   private List<Food> foods = new ArrayList<>();

   @ManyToMany
   @JoinTable(name = "restaurant_foodCategory",
           joinColumns = @JoinColumn(name = "restaurant_id"),
           inverseJoinColumns = @JoinColumn(name = "foodCategory_id")
   )
   private List<FoodCategory> foodCategories = new ArrayList<>();
}
