package com.FOOD.Service;

import com.FOOD.Dto_data_transfer_object.RestaurantDto;
import com.FOOD.Models.Restaurant;
import com.FOOD.Models.User;
import com.FOOD.Request.createRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(createRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId,createRestaurantRequest req)throws Exception;

    public void deleteRestaurant(Long restaurantId)throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long restaurantId) throws Exception;

    public Restaurant findRestaurantByUserId(Long userid) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantIds) throws Exception;

}
