package com.example.appdevfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckOut extends AppCompatActivity {

    private TextView totalPriceTextView;
    private EditText shippingAddressEditText, phoneNumberEditText;
    private RadioGroup paymentMethodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        totalPriceTextView = findViewById(R.id.total_price);
        shippingAddressEditText = findViewById(R.id.shipping_address);
        phoneNumberEditText = findViewById(R.id.phone_number);
        paymentMethodGroup = findViewById(R.id.payment_method_group);
// get total from addcart
        int totalPrice = getIntent().getIntExtra("total_price", 0);

        totalPriceTextView.setText("Total Price: ₱" +totalPrice);


        findViewById(R.id.confirm_button).setOnClickListener(v -> confirmPayment());
    }

    private void confirmPayment() {
        // get data from user
        String address = shippingAddressEditText.getText().toString().trim();
        String phone = phoneNumberEditText.getText().toString().trim();


        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter a shipping address.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() < 10 || !phone.matches("\\d+")) {
            Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            return;
        }

//radio button
        int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
        //check if something is selected
        if (selectedId == -1) {
            Toast.makeText(this, "Please select a payment method.", Toast.LENGTH_SHORT).show();
            return;
        }
//retrieve
        RadioButton selectedRadioButton = findViewById(selectedId);
        String paymentMethod = selectedRadioButton.getText().toString();


        Toast.makeText(this, "Order Success!", Toast.LENGTH_LONG).show();
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }
}