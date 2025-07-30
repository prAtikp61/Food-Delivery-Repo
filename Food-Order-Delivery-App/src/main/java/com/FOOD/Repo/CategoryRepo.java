package com.FOOD.Repo;

import com.FOOD.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    public List<Category> findByRestaurantId(Long id);


}
