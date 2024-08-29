package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @ManyToOne
   private User customer;

   @ManyToOne
   private Restaurant restaurant;
   private double totalAmount;
   private String orderStatus;

   @CreationTimestamp
   private Date createdAt;

   @ManyToOne
   private Address deliveryAddress;

   @OneToMany
   private List<OrderItem> items;

   private int totalItem;
   private double totalPrice;
}
