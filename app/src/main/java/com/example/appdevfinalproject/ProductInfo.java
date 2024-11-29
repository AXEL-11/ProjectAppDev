package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductInfo extends AppCompatActivity {

    TextView productname, productprice, productdescription, productlocation, productspecifications, txt2;
    ImageView productimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);



        productname = findViewById(R.id.Productname);
        productprice = findViewById(R.id.productprice);
        productimage = findViewById(R.id.productimage);
        productdescription = findViewById(R.id.productdescription);
        productlocation = findViewById(R.id.productlocation);
        productspecifications = findViewById(R.id.productspecifications);

        String productName = getIntent().getStringExtra("productName");
        String productPrice = getIntent().getStringExtra("productPrice");
        int productImageResId = getIntent().getIntExtra("productImageResId", 0);
        String productDescription = getIntent().getStringExtra("productDescription");
        String productSpecifications = getIntent().getStringExtra("productSpecifications");
        String productLocation = getIntent().getStringExtra("productLocation");

        productname.setText(productName);
        productprice.setText("₱" + productPrice);
        productimage.setImageResource(productImageResId);
        productdescription.setText(productDescription != null ? productDescription : "No description available.");
        productspecifications.setText(productSpecifications != null ? productSpecifications : "No specifications available.");
        productlocation.setText(productLocation != null ? productLocation : "Unknown location");


    }

    public void onAdd(View v) {
        String username = getIntent().getStringExtra("username");

        if (username == null || username.isEmpty()) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String productName = productname.getText().toString();
        String productPriceString = productprice.getText().toString().replace("₱", "").trim();
        int productImageResId = getIntent().getIntExtra("productImageResId", 0);

        if (productName.isEmpty() || productPriceString.isEmpty()) {
            Toast.makeText(this, "Product name or price cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        double productPrice;
        try {
            if (!productPriceString.matches("\\d+(\\.\\d+)?")) {
                Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
                return;
            }
            productPrice = Double.parseDouble(productPriceString);
        } catch (NumberFormatException e) {
            Log.e("ProductInfo", "Invalid price format", e);
            Toast.makeText(this, "Error parsing price", Toast.LENGTH_SHORT).show();
            return;
        }


        if (productImageResId == 0) {
            Toast.makeText(this, "Invalid product image", Toast.LENGTH_SHORT).show();
            return;
        }


        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addToCart(getIntent().getStringExtra("username"), getIntent().getStringExtra("productName"), productPrice, getIntent().getIntExtra("productImageResId", 0));


        Toast.makeText(this, productName + " added to cart", Toast.LENGTH_SHORT).show();
    }
    public void onCart1(View v) {
        String username = getIntent().getStringExtra("username");

        Log.d("DEBUG", "Received username: " + username);
        // Debugging log to check username
        if (username != null) {
            Log.d("DEBUG", "Username passed: " + username);
        } else {
            Log.e("DEBUG", "Username is null!");
        }

        if (username != null) {
            Intent intent = new Intent(this, AddCart.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username is not available", Toast.LENGTH_SHORT).show();
        }
    }
}