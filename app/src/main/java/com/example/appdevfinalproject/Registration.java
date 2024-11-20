package com.example.appdevfinalproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    EditText etname, etpass, etdate, etmail, etaddress, etphone;
    Button btnsignup;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize UI elements
        etname = findViewById(R.id.etname);
        etpass = findViewById(R.id.etpassword);
        etmail = findViewById(R.id.etmail);
        etaddress = findViewById(R.id.etaddress);
        etphone = findViewById(R.id.etphone);
        etdate = findViewById(R.id.etdate);

        btnsignup = findViewById(R.id.btnsignup);

    }

    public void onSign(View v) {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Get user input
        String username = etname.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        String email = etmail.getText().toString().trim();
        String address = etaddress.getText().toString().trim();
        String phonenumber = etphone.getText().toString().trim();
        String date = etdate.getText().toString().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() ||
                address.isEmpty() || phonenumber.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            return;
        }


        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("phonenumber", phonenumber);
        values.put("password", password);
        values.put("address", address);
        values.put("dateofbirth", date);

        try {

            db.insertOrThrow("users", null, values);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            finish();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Username or email already exists", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}