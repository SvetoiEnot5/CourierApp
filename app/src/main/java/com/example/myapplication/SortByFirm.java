package com.example.myapplication;

import java.util.Comparator;

public class SortByFirm implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getCompany().getName().compareTo(o2.getCompany().getName());
    }
}
