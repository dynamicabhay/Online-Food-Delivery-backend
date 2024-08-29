package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
   private Order orders;

   @ElementCollection
   private List<RestaurantDTO> favorites;

   @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Address> addresses;

   @ColumnDefault("true")
   private boolean status;
}
