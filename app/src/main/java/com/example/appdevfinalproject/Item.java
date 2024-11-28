package com.example.appdevfinalproject;

public class Item {
    private String name;
    private String price;
    private int imageResId;
    private String description;
    private  String specifications;
    private String location;

    public Item(String name, String price, int imageResId, String description, String specifications, String location) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
        this.specifications = specifications;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public String getLocation() {
        return location;
    }
}