package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    @OneToOne
   private User owner;
   private String name;
   private String description;
   private String cuisineType;

   @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Address> address = new ArrayList<>();

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

   private boolean open = true;

   @ManyToMany(cascade = CascadeType.REMOVE)
   @JoinTable(name = "restaurant_food",
           joinColumns = @JoinColumn(name = "restaurant_id"),
           inverseJoinColumns = @JoinColumn(name = "food_id")
   )
   private List<Food> foods = new ArrayList<>();

   @ManyToMany(cascade = CascadeType.REMOVE)
   @JoinTable(name = "restaurant_foodCategory",
           joinColumns = @JoinColumn(name = "restaurant_id"),
           inverseJoinColumns = @JoinColumn(name = "foodCategory_id")
   )
   private List<FoodCategory> foodCategories = new ArrayList<>();
}
