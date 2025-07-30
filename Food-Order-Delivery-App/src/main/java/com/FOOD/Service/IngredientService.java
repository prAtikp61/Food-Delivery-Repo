package com.FOOD.Service;

import com.FOOD.Models.IngredientsCategory;
import com.FOOD.Models.IngredientsList;

import java.util.List;

public interface IngredientService {
    public IngredientsCategory createIngredientsCategory( String name,Long restaurantId) throws Exception;
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;
    public List<IngredientsList> findRestaurantsIngredients(Long restaurantId);

    public IngredientsList createIngredientItem(Long restaurantId, String ingredientName,Long categoryId) throws Exception;

    public IngredientsList updateStocks(Long id) throws Exception;
}
