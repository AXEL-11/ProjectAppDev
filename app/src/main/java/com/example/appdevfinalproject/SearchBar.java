package com.example.appdevfinalproject;

import android.content.Intent;
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
        filteredList = new ArrayList<>();

        populateItems();

// add items or products


        filteredList = new ArrayList<>(itemList);

        String username = getIntent().getStringExtra("username");
        adapter = new ItemAdapter(this, filteredList, username);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterItems(newText);
                return true;
            }
        });
        
    }
    private void populateItems() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.monitorm8);
        images.add(R.drawable.monitor1);
        images.add(R.drawable.monitor2);
        images.add(R.drawable.monitor3);
        itemList.add(new Item("M8 Smart Monitor", 1899000, images, "a versatile 32-inch 4K UHD display that combines the functionality of a traditional monitor with smart TV features, making it suitable for work, entertainment, and gaming.", "Display: 32-inch 4K UHD (3840 x 2160) resolution with HDR10+ support for vivid colors and deep contrast.\nDesign: Slim and stylish build with a height-adjustable and tiltable ergonomic stand.\nPorts: Multiple connectivity options, including USB-C, HDMI, and USB-A, supporting data transfer, device charging, and multi-screen setup", "La union", R.drawable.monitorm8));

        ArrayList<Integer> images1 = new ArrayList<>();
        images1.add(R.drawable.keyboard);
        images1.add(R.drawable.keyboard1);
        images1.add(R.drawable.keyboard2);
        images1.add(R.drawable.keyboard3);
        itemList.add(new Item("Leaven K620 Keyboard", 825, images1, "A durable and stylish mechanical keyboard.", "Connection: Wired (Type-C)\n" +
                "Material: PBT keycaps with injection molding\n" +
                "Lighting Modes: Up to 17 different RGB effects\n" +
                "Compatibility: Works with Windows and macOS systems\n" +
                "Dimensions: Lightweight and portable design optimized for compact setup", "Laguna",R.drawable.keyboard));
        ArrayList<Integer> images2 = new ArrayList<>();
        images2.add(R.drawable.img);
        images2.add(R.drawable.giga);
        images2.add(R.drawable.giga1);
        images2.add(R.drawable.giga2);
        itemList.add(new Item("Gigabyte GeForce RTX 4060 Ti Gaming OC 16GB GDDR6 PCI Express 4.0 Graphics Card - Black", 30700, images2, "The Gigabyte GeForce RTX 4060 Ti Gaming OC 16GB is a powerful graphics card that delivers stunning visuals and fast frame rates for gaming and creative applications. It is powered by NVIDIA's new RTX architecture, which includes enhanced RT cores and tensor cores for AI acceleration. The card also boasts a staggering 16GB of GDDR6 memory. ", "Model: GV-N406T-GAMING-OC-16GD - SKU: 6551341 -\n Memory: 16GB GDDR6 - \nInterface: PCI Express 4.0 - \nFeatures: Enhanced RT cores, tensor cores, AI acceleration "
                , "Laguna",R.drawable.img));
        ArrayList<Integer> images3 = new ArrayList<>();
        images3.add(R.drawable.img_2);
        images3.add(R.drawable.speed);
        images3.add(R.drawable.speed2);
        images3.add(R.drawable.speed3);
        itemList.add(new Item("XFX Speedster Qick 319 AMD Radeon RX 6750 XT Core 12GB GDDR6 PCI Express 4.0 Gaming Graphics Card - Black", 18211, images3, "The XFX Speedster Qick 319 AMD Radeon RX 6750 XT is a powerful graphics card featuring AMD's RDNA 2 architecture. It's designed to deliver an incredible 1080p gaming experience with vivid visuals and elevated experiences.", " Model: RX-675XYJFDR - SKU: 6571303\n" +
                "Memory: 12GB GDDR6 - Interface: PCI Express 4.0\n" +
                "Features: AMD RDNA 2 architecture, 1080p gaming performance \n", "Baguio",R.drawable.img_2));

        ArrayList<Integer> images4 = new ArrayList<>();
        images4.add(R.drawable.img_1);
        images4.add(R.drawable.consair6000);
        images4.add(R.drawable.consair2);
        itemList.add(new Item("Corsair Vengeance RGB 32GB (2 x 16GB) DDR5 6000MHz C36 UDIMM Desktop Memory - White", 6660, images4, "The Corsair Vengeance RGB DDR5 memory delivers high performance, higher frequencies, and greater capacities optimized for Intel motherboards. It features dynamic, individually addressable ten-zone RGB lighting to illuminate your PC. The memory chips enable faster processing, rendering, and buffering than ever before, with onboard voltage regulation for easy overclocking. ", "Model: CMH32GX5M2E6000C36W\n" +
                " Capacity: 32GB (2 x 16GB)\n" +
                "Speed: 6000MHz  \n" +
                " Features: RGB lighting, onboard voltage regulation, Intel XMP 3.0 support ", "Laguna",R.drawable.img_1));

        ArrayList<Integer> images5 = new ArrayList<>();
        images5.add(R.drawable.img_3);
        images5.add(R.drawable.msimag);
        images5.add(R.drawable.msimag1);
        images5.add(R.drawable.msimag3);
        itemList.add(new Item("MSI MAG Z790 Tomahawk Max WiFi (Socket LGA 1700) Intel Z790 ATX DDR5 Wi-Fi 7 Motherboard - Black", 15323, images5, "The MSI MAG Z790 Tomahawk Max WiFi is a high-quality motherboard designed for gamers, providing a stable and durable foundation for PC builds. It comes with a Wi-Fi 7 solution, 2.5G LAN, DDR5 support, PCIe 5.0 solutions, and an exclusive M.2 Shield Frozr. This motherboard is ready for Intel's 14th and 13th Gen Core processors. ", " Model: MAG Z790 Tomahawk Max WiFi - SKU: 6571302\n" +
                " Socket: LGA 1700 - Chipset: Intel Z790\n" +
                " Form Factor: ATX - Memory: DDR5\n" +
                "Features: Wi-Fi 7, 2.5G LAN, PCIe 5.0, M.2 Shield Frozr", "Laguna",R.drawable.img_3));

        ArrayList<Integer> images6 = new ArrayList<>();
        images6.add(R.drawable.img_4);
        images6.add(R.drawable.memory);
        images6.add(R.drawable.memory2);
        images6.add(R.drawable.memory3);
        itemList.add(new Item("Corsair Vengeance 32GB (2 x 16GB) DDR4 5600MHz C40 UDIMM Desktop Memory – Black.", 15323, images6, " The Corsair Vengeance DDR5 memory is optimized for Intel motherboards and delivers high performance. It's a great option for those looking for a balance between speed and affordability. ", " Model: MAG Z790 Tomahawk Max WiFi -  Model: CMK32GX5M2B5600C40 - SKU: 6562313\n" +
                "Capacity: 32GB (2 x 16GB\n" +
                "Speed: 5600MHz - Latency: C40\n"
                , "Pampanga",R.drawable.img_4));

        ArrayList<Integer> images7 = new ArrayList<>();
        images7.add(R.drawable.img_5);
        images7.add(R.drawable.rtx4090);
        images7.add(R.drawable.rtx4901);
        images7.add(R.drawable.rtx40903);
        itemList.add(new Item("NVIDIA GeForce RTX 4090 Ti", 12000, images7, " A top-of-the-line graphics card for extreme gaming and professional workloads.", "CUDA Cores: 18432\n" +
                " Memory: 24GB GDDR6X\n" +
                "Memory Clock Speed: 21Gbps\n"
                , "Bicol",R.drawable.img_5));

        ArrayList<Integer> images8 = new ArrayList<>();
        images8.add(R.drawable.img_6);
        images8.add(R.drawable.samsung);
        images8.add(R.drawable.samsung1);
        images8.add(R.drawable.samsung3);
        itemList.add(new Item("Samsung 990 Pro SSD (1TB)", 10000, images8, " A high-performance NVMe SSD for fast boot times and data transfer speeds", "Capacity: 1TB\n" +
                " Read Speed: Up to 7450MB/s\n" +
                "Write Speed: Up to 6900MB/s\n"
                , "Bataan",R.drawable.img_6));

        ArrayList<Integer> images9 = new ArrayList<>();
        images9.add(R.drawable.img_7);
        images9.add(R.drawable.power);
        images9.add(R.drawable.power1);
        images9.add(R.drawable.power2);
        itemList.add(new Item("Corsair RMx Series 850W Power Supply Unit (PSU)", 15000, images9, "A high-quality, fully modular PSU for powering demanding PC builds.", "Power Output: 850W\n" +
                "   80 PLUS Gold B\n" +
                "Certified\n" +
                "   Fully Modular Design\n"
                , "Bataan",R.drawable.img_7));
    }

    // ipdate displayed items
    private void filterItems(String query) {
        filteredList.clear();

        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(itemList);
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