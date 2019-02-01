package com.pizza.shop.PizzaApplication.model;

/**
 * Created by sangharatna.davane on 1/31/2019.
 */
public class Item {

    private String Order;
    private Long time;


    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
