package com.FOOD.Repo;

import com.FOOD.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r where lower(r.name) like  lower(concat('%',:query,'%')) or" +
            " lower(r.cuisineType) like lower(concat('%',:query,'%')) ")
    List<Restaurant> findBySearchQuery(String searchQuery);

    Restaurant findByOwnerId(Long id);
}
