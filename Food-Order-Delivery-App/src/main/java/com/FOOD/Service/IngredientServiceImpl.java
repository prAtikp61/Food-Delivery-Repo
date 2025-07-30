package com.FOOD.Service;

import com.FOOD.Models.IngredientsCategory;
import com.FOOD.Models.IngredientsList;
import com.FOOD.Models.Restaurant;
import com.FOOD.Repo.IngredientItemRepo;
import com.FOOD.Repo.IngredientsCategoryRepo;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientsCategoryRepo ingredientsCategoryRepo;

    @Autowired
    private IngredientItemRepo ingredientItemRepo;

    @Autowired
    private RestaurantService restaurantService;





    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory=new IngredientsCategory();
        ingredientsCategory.setName(name);
        ingredientsCategory.setRestaurant(restaurant);
        return ingredientsCategoryRepo.save(ingredientsCategory);

    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> ingredientsCategory=ingredientsCategoryRepo.findById(id);
        if(ingredientsCategory.isEmpty())
        {
            throw new Exception("Ingredient Category Not Found");
        }

        return ingredientsCategory.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientsCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public List<IngredientsList> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsList createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientsList ingredientsList=new IngredientsList();
        IngredientsCategory ingredientsCategory=findIngredientsCategoryById(categoryId);
        ingredientsList.setRestaurant(restaurant);
        ingredientsList.setName(ingredientName);
        ingredientsList.setIngredientsCategory(ingredientsCategory);
        IngredientsList ingredientsList11=ingredientItemRepo.save(ingredientsList);
        ingredientsCategory.getIngredients().add(ingredientsList11);
        return ingredientsList11 ;
    }

    @Override
    public IngredientsList updateStocks(Long id) throws Exception {
        Optional<IngredientsList> ingredientsList=ingredientItemRepo.findById(id);
        if(ingredientsList.isEmpty())
        {
            throw new Exception("Ingredient Item Not Found");
        }
        IngredientsList ingredientsList1=ingredientsList.get();
        ingredientsList1.setInstock(!ingredientsList1.isInstock());
        return ingredientItemRepo.save(ingredientsList1);
    }
}
