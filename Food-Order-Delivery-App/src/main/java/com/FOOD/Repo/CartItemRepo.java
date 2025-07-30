package com.FOOD.Repo;

import com.FOOD.Models.cartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<cartItems,Long> {

}
