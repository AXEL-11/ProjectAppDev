package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainPage extends AppCompatActivity {


    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        searchView = findViewById(R.id.searchView);


    }
    public void onsearch(View v){
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, SearchBar.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void Onitem1(View v){
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, ProductInfo.class);
        intent.putExtra("productName", "M8 Smart Monitor");
        intent.putExtra("productPrice", "18990.00");
        intent.putExtra("productImageResId", R.drawable.monitorm8);
        intent.putExtra("productDescription", " a versatile 32-inch 4K UHD display that combines the functionality of a traditional monitor with smart TV features, making it suitable for work, entertainment, and gaming.");
        intent.putExtra("productSpecifications", "Display: 32-inch 4K UHD (3840 x 2160) resolution with HDR10+ support for vivid colors and deep contrast.\nDesign: Slim and stylish build with a height-adjustable and tiltable ergonomic stand.\nPorts: Multiple connectivity options, including USB-C, HDMI, and USB-A, supporting data transfer, device charging, and multi-screen setup");
        intent.putExtra("productLocation", "La Union");
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void Onitem2(View v){
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, ProductInfo.class);
        intent.putExtra("productName", "Leaven K620 Keyboard");
        intent.putExtra("productPrice", "825.99");
        intent.putExtra("productImageResId", R.drawable.keyboard);
        intent.putExtra("productDescription", " a compact 61-key mechanical keyboard designed for gaming and productivity.");
        intent.putExtra("productSpecifications", "Connection: Wired (Type-C)\n" +
                "Material: PBT keycaps with injection molding\n" +
                "Lighting Modes: Up to 17 different RGB effects\n" +
                "Compatibility: Works with Windows and macOS systems\n" +
                "Dimensions: Lightweight and portable design optimized for compact setup");
        intent.putExtra("productLocation", "Laguna");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void Onitem3(View v){
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, ProductInfo.class);
        intent.putExtra("productName", "Gigabyte GeForce RTX 4060 Ti Gaming OC 16GB GDDR6 PCI Express 4.0 Graphics Card - Black");
        intent.putExtra("productPrice", "30000");
        intent.putExtra("productImageResId", R.drawable.img);
        intent.putExtra("productDescription", "The Gigabyte GeForce RTX 4060 Ti Gaming OC 16GB is a powerful graphics card that delivers stunning visuals and fast frame rates for gaming and creative applications. It is powered by NVIDIA's new RTX architecture, which includes enhanced RT cores and tensor cores for AI acceleration. The card also boasts a staggering 16GB of GDDR6 memory. ");
        intent.putExtra("productSpecifications", "Model: GV-N406T-GAMING-OC-16GD - SKU: 6551341 -\n Memory: 16GB GDDR6 - \nInterface: PCI Express 4.0 - \nFeatures: Enhanced RT cores, tensor cores, AI acceleration ");
        intent.putExtra("productLocation", "La Union");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void Onitem4(View v){
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, ProductInfo.class);
        intent.putExtra("productName", "Corsair Vengeance RGB 32GB (2 x 16GB) DDR5 6000MHz C36 UDIMM Desktop Memory - White");
        intent.putExtra("productPrice", "6660");
        intent.putExtra("productImageResId", R.drawable.img_1);
        intent.putExtra("productDescription", "Corsair Vengeance RGB 32GB (2 x 16GB) DDR5 6000MHz C36 UDIMM Desktop Memory - White");
        intent.putExtra("productSpecifications", " Model: CMH32GX5M2E6000C36W - SKU: 6562319 - Capacity: 32GB (2 x 16GB) - Speed: 6000MHz - Latency: C36 - Features: RGB lighting, onboard voltage regulation, Intel XMP 3.0 support ");
        intent.putExtra("productLocation", "Baguio");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void Onitem5(View v){
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, ProductInfo.class);
        intent.putExtra("productName", "NVIDIA GeForce RTX 4090 Ti");
        intent.putExtra("productPrice", "12000");
        intent.putExtra("productImageResId", R.drawable.img_5);
        intent.putExtra("productDescription", "A top-of-the-line graphics card for extreme gaming and professional workloads.");
        intent.putExtra("productSpecifications","CUDA Cores: 18432\n" +
                " Memory: 24GB GDDR6X\n" +
                "Memory Clock Speed: 21Gbps\n");
        intent.putExtra("productLocation", "Bicol");
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void OnUser(View v) {
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, UserProfile.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onCart(View v) {
        String username = getIntent().getStringExtra("username");

        Log.d("DEBUG", "Received username: " + username);

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