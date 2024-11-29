package com.example.appdevfinalproject;

public class CartItem {
    private String productName;
    private double productPrice;
    private int productImage;
    private String username; // Added field for username


    public CartItem(String username, String productName, double productPrice, int productImage) {
        this.username = username;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }


    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getUsername() {
        return username; // Getter for username
    }
}