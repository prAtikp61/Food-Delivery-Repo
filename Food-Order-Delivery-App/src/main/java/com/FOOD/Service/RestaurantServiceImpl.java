package com.FOOD.Service;

import com.FOOD.Dto_data_transfer_object.RestaurantDto;
import com.FOOD.Models.Addresses;
import com.FOOD.Models.Restaurant;
import com.FOOD.Models.User;
import com.FOOD.Repo.RestaurantRepo;
import com.FOOD.Repo.addressRepo;
import com.FOOD.Repo.userRepo;
import com.FOOD.Request.createRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private addressRepo addressRepo;

    @Autowired
    private userRepo userRepository;



    @Override
    public Restaurant createRestaurant(createRestaurantRequest req, User user) {
        Addresses address = addressRepo.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInfo(req.getContactInfo());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setName(req.getName());
        restaurant.setImages(req.getImages());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, createRestaurantRequest req) throws Exception {
      Restaurant restaurant=findRestaurantById(restaurantId);

      if(restaurant.getCuisineType()!=null){
          restaurant.setCuisineType(req.getCuisineType());
      }
      if(restaurant.getDescription()!=null)
      {
          restaurant.setDescription(req.getDescription());
      }
      if(restaurant.getName()!=null){
          restaurant.setName(req.getName());
      }

        return restaurantRepo.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
       Restaurant restaurant=findRestaurantById(restaurantId);
       restaurantRepo.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {

        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {

        Optional<Restaurant> opt=restaurantRepo.findById(restaurantId);

        if(opt.isEmpty())
        {
            throw new Exception("restaurant not found");
        }
        return opt.get();
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userid) throws Exception {
       Restaurant restaurant=restaurantRepo.findByOwnerId(userid);
       if(restaurant==null)
       {
           throw new Exception("restaurant not found");
       }

       return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);

        RestaurantDto dto =new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setPhotos(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

      boolean isfav=false;
      List<RestaurantDto> favorites=user.getFavorites();
      for(RestaurantDto favorite:favorites){
          if(favorite.getId().equals(restaurantId))
          {
              isfav=true;
              break;
          }
      }

      if(isfav)
      {
          favorites.removeIf((favorite)->favorite.getId().equals(restaurantId));
      }
      else{
          favorites.add(dto);
      }


        userRepository.save(user);

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantIds) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantIds);
        restaurant.setOpen(!restaurant.isOpen());


        return restaurantRepo.save(restaurant);
    }
}
