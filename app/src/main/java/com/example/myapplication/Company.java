package com.example.myapplication;

public class Company {

    public Company(String name, String address) {
        this.address = address;
        this.name = name;
    }

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
