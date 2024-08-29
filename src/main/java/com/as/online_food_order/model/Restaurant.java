package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

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
   private List<Order> orders;

   private int numRating;

   @ElementCollection
   @Column(name = "url")
   private List<String> images;

   @CreationTimestamp
   private Date registrationDate;

   @ColumnDefault("true")
   private boolean open;

   @ManyToMany(mappedBy = "restaurants")
   private List<Food> foods;

   @ManyToMany(mappedBy = "restaurants")
   private List<FoodCategory> foodCategories;
}
