package com.FOOD.Controllers;

import com.FOOD.Dto_data_transfer_object.RestaurantDto;
import com.FOOD.Models.Restaurant;
import com.FOOD.Models.User;
import com.FOOD.Request.createRestaurantRequest;
import com.FOOD.Service.RestaurantService;
import com.FOOD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt,
                                                             @RequestParam String keyword

    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant=restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getallRestaurants(@RequestHeader("Authorization") String jwt

    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant=restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findrestaurantbyId(@RequestHeader("Authorization") String jwt,
                                                               @PathVariable Long id

    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long id

    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurantdto=restaurantService.addToFavorites(id,user);
        return new ResponseEntity<>(restaurantdto, HttpStatus.OK);
    }
}
