package com.example.myapplication;

public class SmallPack extends Package {
    public SmallPack(String size, boolean fragility, String from, String to) {
        super(from, to);
        this.fragility = fragility;
        this.size = size;
        this.setRequirements("Требования отсутствуют");
    }

    public String getType() {
        return "М";
    }
}