package com.FOOD.Service;

import com.FOOD.Models.*;
import com.FOOD.Repo.OrderItemRepo;
import com.FOOD.Repo.OrderRepo;
import com.FOOD.Repo.addressRepo;
import com.FOOD.Repo.userRepo;
import com.FOOD.Request.OrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private addressRepo repo1;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderReq req, User user) throws Exception {

        Addresses shippingAddress = req.getDeliveryAdd();
        Addresses save_add=repo1.save(shippingAddress);
        if(!user.getAddressess().contains(save_add)){
            user.getAddressess().add(save_add);
            userRepo.save(user);
        }
        Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());
        Order createdOrder=new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant);
        createdOrder.setDeliveryAddress(save_add);

        Cart cart=cartService.findCartByUserId(user.getId());


        List<orderItem> orderItems=new ArrayList<>();

        for(cartItems cartItems:cart.getCartItems()){
            orderItem orderItem=new orderItem();
            orderItem.setFood(cartItems.getFood());
            orderItem.setQuantity(cartItems.getQuantity());
            orderItem.setIngredients(cartItems.getIngredients());
            orderItem.setTotalPrice(cartItems.getTotalPrice());

            orderItem savedOrderItems=orderItemRepo.save(orderItem);
            orderItems.add(savedOrderItems);
        }
        Long totalPrice=cartService.calculateCartTotal(cart);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder=orderRepo.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String status) throws Exception {
        Order order=findOrderById(orderId);
        if(status.equals("OUT_FOR_DELIVERY")|| status.equals("DELIVERED") || status.equals("COMPLETED") || status.equals("PENDING") ){
            order.setOrderStatus(status);
            return orderRepo.save(order);
        }
        throw new Exception("please select a valid order status");

    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order=findOrderById(orderId);
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {

        return orderRepo.findByCustomer_Id(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders= orderRepo.findByRestaurantId(restaurantId);
        if(orderStatus!=null)
        {
            orders=orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());

        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optorder=orderRepo.findById(orderId);
        if(optorder.isEmpty())
        {
            throw new Exception("Order not found");
        }

        return optorder.get();
    }
}
