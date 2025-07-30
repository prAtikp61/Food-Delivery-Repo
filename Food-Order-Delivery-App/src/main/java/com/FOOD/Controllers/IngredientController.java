package com.FOOD.Controllers;

import com.FOOD.Models.IngredientsCategory;
import com.FOOD.Models.IngredientsList;
import com.FOOD.Request.IngredientCategoryReq;
import com.FOOD.Request.IngredientItemReq;
import com.FOOD.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientsCategory(@RequestBody IngredientCategoryReq req) throws Exception {
        IngredientsCategory ingredientsCategory=ingredientService.createIngredientsCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<IngredientsList> createIngredientsItemList(@RequestBody IngredientItemReq req) throws Exception {
        IngredientsList item=ingredientService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsList> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientsList ingredientsList=ingredientService.updateStocks(id);
        return new ResponseEntity<>(ingredientsList, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsList>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientsList> ingredientsList=ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(ingredientsList, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientsCategory> ingredientsCategory=ingredientService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.OK);
    }
}
