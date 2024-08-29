package com.as.online_food_order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long  id;
  @ManyToOne
  private Cart  cart;

  @ManyToOne
  private Food  food;
  private int  quantity;
  private Float  totalPrice;
}
