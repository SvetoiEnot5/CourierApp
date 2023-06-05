package com.example.myapplication;

import java.util.Comparator;

public class SortByType implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getPack().getType().compareTo(o2.getPack().getType());
    }
}
