package com.example.appdevfinalproject;

import android.os.Bundle;
import android.text.TextUtils;
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

public class SearchBar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList;
    private List<Item> filteredList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_bar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView = findViewById(R.id.searchView);

        recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);


        itemList = new ArrayList<>();
        itemList.add(new Item("M8 Smart Monitor", "₱18,990.00", R.drawable.monitorm8, "A smart monitor with excellent features."));
        itemList.add(new Item("Leaven K620 Keyboard", "₱825.99", R.drawable.keyboard, "A durable and stylish mechanical keyboard."));
        // Add more items to the list...

        filteredList = new ArrayList<>(itemList); // Initialize with all items


        adapter = new ItemAdapter(this, filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // Set up SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle query submission (if necessary)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list based on query
                filterItems(newText);
                return true;
            }
        });
        
    }

    private void filterItems(String query) {
        filteredList.clear();

        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(itemList); // Show all items if no search query
        } else {
            for (Item item : itemList) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }


        adapter.notifyDataSetChanged();
    }
}