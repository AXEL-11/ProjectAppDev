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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        dbHelper = new DBHelper(this);
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.etEmail);
        tvDateOfBirth = findViewById(R.id.etDateOfBirth);
        tvAddress = findViewById(R.id.etAddress);
        tvPhoneNumber = findViewById(R.id.etPhoneNumber);

        // Get the username passed via intent
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            loadUserProfile(username);
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadUserProfile(String username) {
        // Retrieve user details from the database
        Cursor cursor = dbHelper.getUserDetails(username);
        if (cursor != null && cursor.moveToFirst()) {
            try {

                String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));
                String dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATEOFBIRTH));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PHONENUMBER));


                tvUsername.setText("Username: " + username);
                tvEmail.setText("Email: " + (email != null ? email : "N/A"));
                tvDateOfBirth.setText("Date of Birth: " +(dateOfBirth != null ? dateOfBirth : "N/A"));
                tvAddress.setText("Address: " +(address != null ? address : "N/A"));
                tvPhoneNumber.setText("Phone Number: " +(phoneNumber != null ? phoneNumber : "N/A"));
            } finally {

                cursor.close();
            }
        } else {
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
        }
    }
    // deletes the user profile
    public void onDelete(View v) {
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            boolean isDeleted = dbHelper.deleteUser(username);
            if (isDeleted) {
                Toast.makeText(this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // edit the user profile
    public void onEdit(View v) {
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    //logout the user
    public void onLogout(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}