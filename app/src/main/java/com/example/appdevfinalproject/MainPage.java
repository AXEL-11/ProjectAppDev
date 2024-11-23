package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        Intent intent = new Intent(this, SearchBar.class);
        startActivity(intent);
    }
    public void Onitem1(View v){
        Intent intent = new Intent(this, ProductInfo.class);
        intent.putExtra("productName", "M8 Smart Monitor");
        intent.putExtra("productPrice", "₱18,990.00");
        intent.putExtra("productImageResId", R.drawable.monitorm8);
        intent.putExtra("productDescription", "A smart monitor with excellent features.");

        startActivity(intent);
    }
}