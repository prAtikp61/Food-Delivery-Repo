package com.FOOD.Repo;

import com.FOOD.Models.IngredientsList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IngredientItemRepo extends JpaRepository<IngredientsList,Long> {

    List<IngredientsList> findByRestaurantId(Long id);

}
