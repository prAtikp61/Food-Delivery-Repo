package com.FOOD.Service;

import com.FOOD.Models.Cart;
import com.FOOD.Models.Food;
import com.FOOD.Models.User;
import com.FOOD.Models.cartItems;
import com.FOOD.Repo.CartItemRepo;
import com.FOOD.Repo.CartRepo;
import com.FOOD.Repo.FoodRepo;
import com.FOOD.Request.AddCartItemReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private FoodService foodService;

    @Override
    public cartItems addToCart(AddCartItemReq req, String jwt) throws Exception {
       User user= userService.findUserByJwtToken(jwt);
       Food food=foodService.findFoodById(req.getFoodId());
       Cart cart=cartRepo.findByCustomerId(user.getId());

       for(cartItems cartItem:cart.getCartItems()){
           if(cartItem.getFood().equals(food))
           {
               int newQuantity=cartItem.getQuantity()+req.getQuantity();
               return updateCartItemQuantity(cartItem.getId(),newQuantity);
           }
       }

       cartItems newCartItems=new cartItems();
       newCartItems.setFood(food);
       newCartItems.setQuantity(req.getQuantity());
       newCartItems.setCart(cart);
       newCartItems.setIngredients(req.getIngredients());
       newCartItems.setTotalPrice(req.getQuantity()* food.getPrice());
     cartItems cartItems=cartItemRepo.save(newCartItems);
     cart.getCartItems().add(cartItems);
        return cartItems;
    }

    @Override
    public cartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
       Optional<cartItems> optcartItems=cartItemRepo.findById(cartItemId);
       if(optcartItems.isEmpty()){
           throw new Exception("CartItem not found");
       }

        cartItems cartItem=optcartItems.get();
       cartItem.setQuantity(quantity);
       cartItem.setTotalPrice(cartItem.getFood().getPrice()*quantity);

        return cartItemRepo.save(cartItem);
    }

    @Override
    public Cart removeCartItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Cart cart=cartRepo.findByCustomerId(user.getId());
        Optional<cartItems> optcartItems=cartItemRepo.findById(cartItemId);
        if(optcartItems.isEmpty()){
            throw new Exception("CartItem not found");
        }
        cartItems item=optcartItems.get();
        cart.getCartItems().remove(item);
        return cartRepo.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total=0L;
        for(cartItems cartItem:cart.getCartItems()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long Id) throws Exception {
        Optional<Cart> optcart=cartRepo.findById(Id);
        if(optcart.isEmpty()){
            throw new Exception("CartItem not found");
        }
        return optcart.get();
    }

    @Override
    public Cart findCartByUserId(Long id) throws Exception {
        Cart cart=cartRepo.findByCustomerId(id);
        cart.setTotal(calculateCartTotal(cart));

        return cart;

    }

    @Override
    public Cart clearCart(Long id) throws Exception {
      Cart cart=cartRepo.findByCustomerId(id);
      cart.getCartItems().clear();
        return cartRepo.save(cart);
    }
}
