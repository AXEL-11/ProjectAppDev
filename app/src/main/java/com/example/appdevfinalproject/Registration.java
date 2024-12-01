package com.example.appdevfinalproject;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Registration extends AppCompatActivity {

    EditText etname, etpass, etdate, etmail, etaddress, etphone;
    Button btnsignup;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        etname = findViewById(R.id.etname);
        etpass = findViewById(R.id.etpassword);
        etmail = findViewById(R.id.etmail);
        etaddress = findViewById(R.id.etaddress);
        etphone = findViewById(R.id.etphone);
        etdate = findViewById(R.id.etdate);
        btnsignup = findViewById(R.id.btnsignup);


        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }
// calendart
    private void showDatePicker() {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                        String formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                        etdate.setText(formattedDate);
                    }
                },
                year,
                month,
                day
        );


        datePickerDialog.show();
    }
// sign in
    public void onSign(View v) {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String username = etname.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        String email = etmail.getText().toString().trim();
        String address = etaddress.getText().toString().trim();
        String phonenumber = etphone.getText().toString().trim();
        String date = etdate.getText().toString().trim();


        if (username.isEmpty() || password.isEmpty() || email.isEmpty() ||
                address.isEmpty() || phonenumber.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPhoneNumber(phonenumber)) {
            Toast.makeText(this, "Invalid phone number! Must be 10 digits.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidDate(date)) {
            Toast.makeText(this, "Invalid date! Use format YYYY-MM-DD.", Toast.LENGTH_SHORT).show();
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

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\d{11}$");
    }

    private boolean isValidDate(String date) {
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}