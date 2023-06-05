package com.example.myapplication;

public class BigPack extends Package {

    public BigPack(String size, boolean fragility, double weight, String from, String to) {
        super(from, to);
        this.fragility = fragility;
        this.size = size;
        this.weight = weight;
        this.setRequirements("Необходима машина");
    }

    private double weight;

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return "Б";
    }
}