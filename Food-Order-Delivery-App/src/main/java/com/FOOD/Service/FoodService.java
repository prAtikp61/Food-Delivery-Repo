package com.FOOD.Service;

import com.FOOD.Models.Category;
import com.FOOD.Models.Food;
import com.FOOD.Models.Restaurant;
import com.FOOD.Request.createFoodRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoodService {

    public Food createFood(createFoodRequest reg, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isvegetarian,
                                         boolean isnonveg,
                                         boolean isseasonal,
                                         String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long id)throws Exception;
    public Food updateAvailabiltyStatus(Long id)throws Exception;


}
