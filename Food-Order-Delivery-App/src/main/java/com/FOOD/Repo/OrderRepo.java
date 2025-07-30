package com.FOOD.Repo;

import com.FOOD.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    public List<Order> findByCustomer_Id(Long customerId);
    public List<Order> findByRestaurantId(Long restaurantId);


}
