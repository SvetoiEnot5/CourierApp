package com.example.myapplication;

import java.util.Comparator;

public class SortByCost implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (Double.parseDouble(o1.getCost()) == Double.parseDouble(o2.getCost())){
            return 0;
        }
        if (Double.parseDouble(o1.getCost()) > Double.parseDouble(o2.getCost())){
            return 1;
        }
        return -1;
    }
}
