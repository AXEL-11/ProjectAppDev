package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductInfo extends AppCompatActivity {

    // Declare the views
    TextView productname, productprice, productdescription;
    ImageView productimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_product_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        productimage = findViewById(R.id.productimage);
        productname = findViewById(R.id.Productname);
        productprice = findViewById(R.id.productprice);
        productdescription = findViewById(R.id.productdescription);


        String productName = getIntent().getStringExtra("productName");
        String productPrice = getIntent().getStringExtra("productPrice");
        int productImageResId = getIntent().getIntExtra("productImageResId", 0);
        String productDescription = getIntent().getStringExtra("productDescription");


        productname.setText(productName);
        productprice.setText(productPrice);
        productimage.setImageResource(productImageResId);
        productdescription.setText(productDescription);
    }
    public void onsearch(View v){
        Intent intent = new Intent(this, SearchBar.class);
        startActivity(intent);
    }
}