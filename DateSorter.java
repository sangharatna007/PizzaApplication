package com.pizza.shop.PizzaApplication.model;

import java.util.Comparator;

/**
 * Created by sangharatna.davane on 1/31/2019.
 */
public class DateSorter implements Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
