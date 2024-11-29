package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddCart extends AppCompatActivity {

    private LinearLayout cartItemContainer;
    private Button checkoutButton;
    private DBHelper dbHelper;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);


        cartItemContainer = findViewById(R.id.cart_item_container);
        checkoutButton = findViewById(R.id.checkout_button);
        dbHelper = new DBHelper(this);

        String username = getIntent().getStringExtra("username");
        if (username == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        cartItems = dbHelper.getCartItems(username);

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
        } else {
            populateCartItems();
        }

        checkoutButton.setOnClickListener(v -> proceedToCheckout());
    }

    private void populateCartItems() {
        cartItemContainer.removeAllViews();

        for (CartItem item : cartItems) {

            View cartItemView = LayoutInflater.from(this).inflate(R.layout.cart_item, cartItemContainer, false);


            CheckBox itemCheckBox = cartItemView.findViewById(R.id.cart_item_checkbox);
            ImageView productImage = cartItemView.findViewById(R.id.cart_item_image);
            TextView productName = cartItemView.findViewById(R.id.cart_item_name);
            TextView productPrice = cartItemView.findViewById(R.id.cart_item_price);
            Spinner quantitySpinner = cartItemView.findViewById(R.id.cart_item_quantity);


            productImage.setImageResource(item.getProductImage());
            productName.setText(item.getProductName());
            productPrice.setText("₱" + item.getProductPrice());


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.quantity_options, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            quantitySpinner.setAdapter(adapter);


            itemCheckBox.setChecked(false);


            cartItemContainer.addView(cartItemView);
        }
    }

    private void proceedToCheckout() {
        double total = 0;


        for (int i = 0; i < cartItemContainer.getChildCount(); i++) {
            View cartItemView = cartItemContainer.getChildAt(i);
            CheckBox itemCheckBox = cartItemView.findViewById(R.id.cart_item_checkbox);
            Spinner quantitySpinner = cartItemView.findViewById(R.id.cart_item_quantity);

            if (itemCheckBox.isChecked()) {

                int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());


                CartItem item = cartItems.get(i);


                total += item.getProductPrice() * quantity;
            }
        }


        Intent intent = new Intent(this, CheckOut.class);
        intent.putExtra("total_price", total);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}