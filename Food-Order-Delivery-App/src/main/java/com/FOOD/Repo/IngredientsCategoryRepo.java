package com.FOOD.Repo;

import com.FOOD.Models.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsCategoryRepo extends JpaRepository<IngredientsCategory, Long> {

    List<IngredientsCategory> findByRestaurantId(Long id);

}
