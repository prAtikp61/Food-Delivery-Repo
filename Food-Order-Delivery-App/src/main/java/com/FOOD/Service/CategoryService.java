package com.FOOD.Service;

import com.FOOD.Models.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long id) throws Exception;
    public List<Category> findCategoryByRestaurantId(Long restid) throws Exception;
    public Category findCategoryById(Long id) throws Exception;


}
