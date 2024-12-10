package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class ProductInfo extends AppCompatActivity {

    TextView productname, productprice, productdescription, productlocation, productspecifications, productImage;
    ViewPager2 productImagePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        // Initialize views
        productname = findViewById(R.id.Productname);
        productprice = findViewById(R.id.productprice);
        productdescription = findViewById(R.id.productdescription);
        productlocation = findViewById(R.id.productlocation);
        productspecifications = findViewById(R.id.productspecifications);

        ViewPager2 productImageViewPager = findViewById(R.id.productImageViewPager);

        // Get data from Intent

        String productName = getIntent().getStringExtra("productName");
        int productPrice = getIntent().getIntExtra("productPrice", 0);
        String productDescription = getIntent().getStringExtra("productDescription");
        String productSpecifications = getIntent().getStringExtra("productSpecifications");
        String productLocation = getIntent().getStringExtra("productLocation");
        ArrayList<Integer> productImages = getIntent().getIntegerArrayListExtra("productImages");

        // Populate the views
        productname.setText(productName);
        productprice.setText("₱" + productPrice);
        productdescription.setText(productDescription != null ? productDescription : "No description available.");
        productspecifications.setText(productSpecifications != null ? productSpecifications : "No specifications available.");
        productlocation.setText(productLocation != null ? productLocation : "Unknown location");

        // Set up the ViewPager2 with the adapter
        if (productImages != null && !productImages.isEmpty()) {
            ImagePagerAdapter adapter = new ImagePagerAdapter(this, productImages);
            productImageViewPager.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No images available for this product", Toast.LENGTH_SHORT).show();
        }


        Button buyNowButton = findViewById(R.id.button3);
        buyNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductInfo.this, CheckOut.class);
            intent.putExtra("total_price", productPrice);
            startActivity(intent);
        });
    }

    // Add to cart functionality
    public void onAdd(View v) {
        String username = getIntent().getStringExtra("username");

        if (username == null || username.isEmpty()) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String productName = productname.getText().toString();
        String productPriceString = productprice.getText().toString().replace("₱", "").trim();

        if (productName.isEmpty() || productPriceString.isEmpty()) {
            Toast.makeText(this, "Product name or price cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        double productPrice;
        try {
            productPrice = Double.parseDouble(productPriceString);
        } catch (NumberFormatException e) {
            Log.e("ProductInfo", "Invalid price format", e);
            Toast.makeText(this, "Error parsing price", Toast.LENGTH_SHORT).show();
            return;
        }

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addToCart(username, productName, productPrice, getIntent().getIntExtra("productImage", 0));
        Toast.makeText(this, productName + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // View cart functionality
    public void onCart1(View v) {
        String username = getIntent().getStringExtra("username");

        if (username != null) {
            Intent intent = new Intent(this, AddCart.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username is not available", Toast.LENGTH_SHORT).show();
        }
    }
}