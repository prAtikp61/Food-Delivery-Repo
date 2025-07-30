package com.FOOD.Repo;

import com.FOOD.Models.orderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<orderItem, Long> {
}
