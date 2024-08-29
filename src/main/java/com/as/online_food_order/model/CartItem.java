package com.as.online_food_order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

  private Long  id;
  @ManyToOne
  private Cart  cart;
  private Food  food;
  private int  quantity;
  private Float  totalPrice;
}
