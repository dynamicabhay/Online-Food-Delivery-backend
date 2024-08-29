package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String fullName;
   private String email;
   private String password;
   @ManyToOne
   private Role role;

   @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Order> orders;

   @ElementCollection
   private List<RestaurantDTO> favorites = new ArrayList<>();

   @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Address> addresses = new ArrayList<>();

   @ColumnDefault("true")
   private boolean status;
}
