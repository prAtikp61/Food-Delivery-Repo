package com.FOOD.Service;

import com.FOOD.Models.Order;
import com.FOOD.Models.User;
import com.FOOD.Request.OrderReq;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderReq req, User user) throws Exception;
    public Order updateOrder(Long orderId,String status) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUsersOrder(Long userId) throws Exception;
    public List<Order> getRestaurantsOrder(Long restaurantId,String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
}
