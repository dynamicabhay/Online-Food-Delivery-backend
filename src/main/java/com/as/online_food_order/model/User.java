package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String fullName;
   private String email;
   private String password;
   @ManyToOne
   private Role role;

   @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Order> orders = new ArrayList<>();

   @ElementCollection
   private List<RestaurantDTO> favorites = new ArrayList<>();

   @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Address> addresses = new ArrayList<>();

   @ColumnDefault("true")
   private boolean status;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority("ROLE_" + this.getRole().getName().name()));
   }

   @Override
   public String getUsername() {
      return this.email;
   }
}
