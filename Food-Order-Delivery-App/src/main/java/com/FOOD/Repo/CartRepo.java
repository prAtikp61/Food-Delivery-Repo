package com.FOOD.Repo;

import com.FOOD.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    public Cart findByCustomerId(Long userId);
}
