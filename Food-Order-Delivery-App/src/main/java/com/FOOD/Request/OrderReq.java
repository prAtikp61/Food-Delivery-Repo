package com.FOOD.Request;

import com.FOOD.Models.Addresses;

public class OrderReq {
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Addresses getDeliveryAdd() {
        return deliveryAdd;
    }

    public void setDeliveryAdd(Addresses deliveryAdd) {
        this.deliveryAdd = deliveryAdd;
    }

    private Long restaurantId;
    private Addresses deliveryAdd;
}
