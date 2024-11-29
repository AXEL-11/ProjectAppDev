package com.example.appdevfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfile extends AppCompatActivity {

    private TextView tvUsername, tvEmail, tvDateOfBirth, tvAddress, tvPhoneNumber;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Apply edge-to-edge layout if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize DBHelper and UI components
        dbHelper = new DBHelper(this);
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);

        // Get the username passed via intent
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            loadUserProfile(username);
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            finish(); // Exit activity if no username is provided
        }
    }

    private void loadUserProfile(String username) {
        // Retrieve user details from the database
        Cursor cursor = dbHelper.getUserDetails(username);
        if (cursor != null && cursor.moveToFirst()) {
            try {
                // Extract user details
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));
                String dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATEOFBIRTH));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PHONENUMBER));

                // Populate the TextViews with user data
                tvUsername.setText(username);
                tvEmail.setText(email != null ? email : "N/A");
                tvDateOfBirth.setText(dateOfBirth != null ? dateOfBirth : "N/A");
                tvAddress.setText(address != null ? address : "N/A");
                tvPhoneNumber.setText(phoneNumber != null ? phoneNumber : "N/A");
            } finally {
                // Ensure the cursor is closed
                cursor.close();
            }
        } else {
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLogout(View v) {
        // Finish the current activity and navigate to the login page
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close the UserProfile activity
    }
}