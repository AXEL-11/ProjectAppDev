package com.example.appdevfinalproject;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {

    private EditText etEmail, etDateOfBirth, etAddress, etPhoneNumber;
    private DBHelper dbHelper;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);
        etEmail = findViewById(R.id.etEmail);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etAddress = findViewById(R.id.etAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);

        username = getIntent().getStringExtra("username");
        loadUserData(username);


        etDateOfBirth.setOnClickListener(v -> showDatePickerDialog());
    }
// set the current data
    private void loadUserData(String username) {
        Cursor cursor = dbHelper.getUserDetails(username);
        if (cursor != null && cursor.moveToFirst()) {
            try {
                etEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL)));
                etDateOfBirth.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATEOFBIRTH)));
                etAddress.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS)));
                etPhoneNumber.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PHONENUMBER)));
            } finally {
                cursor.close();
            }
        } else {
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void showDatePickerDialog() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the date and set it to the EditText
                    String formattedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    etDateOfBirth.setText(formattedDate);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }
// save new data
    public void onSave(View v) {
        String email = etEmail.getText().toString().trim();
        String dateOfBirth = etDateOfBirth.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();

        boolean isUpdated = dbHelper.updateUserProfile(username, email, dateOfBirth, address, phoneNumber);
        if (isUpdated) {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }
}