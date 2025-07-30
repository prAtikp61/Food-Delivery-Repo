package com.FOOD.Service;

import com.FOOD.Models.Category;
import com.FOOD.Models.Restaurant;
import com.FOOD.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorySericeImpl  implements CategoryService{



    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public Category createCategory(String name, Long id) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantByUserId(id);
        Category category=new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restid) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restid);
        return categoryRepo.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category=categoryRepo.findById(id);

        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return category.get();
    }
}
