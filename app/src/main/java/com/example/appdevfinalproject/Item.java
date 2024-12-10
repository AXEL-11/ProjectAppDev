package com.example.appdevfinalproject;

import java.util.ArrayList;

public class Item {
    private String name;
    private Integer price;
    private ArrayList<Integer> imageResId;
    private String description;
    private  String specifications;
    private String location;
    private Integer image;

    public Item(String name, Integer price, ArrayList<Integer> imageResId, String description, String specifications, String location, int image) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
        this.specifications = specifications;
        this.location = location;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Integer> getImageResId() {
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

    public int getImage() {
        return image;
    }
}