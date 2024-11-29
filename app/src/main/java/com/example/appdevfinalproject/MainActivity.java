package com.example.appdevfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText etus, etpw;
    Button btnlogin;
    TextView txtcreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etus = (EditText) findViewById(R.id.etus);
        etpw = (EditText) findViewById(R.id.etpw);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        txtcreate = (TextView) findViewById(R.id.txtcreate);
        dbHelper = new DBHelper(this);
    }
    public void onLogin(View v) {
        String username = etus.getText().toString().trim();  // Trim to avoid unwanted spaces
        String password = etpw.getText().toString().trim();  // Trim to avoid unwanted spaces

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Log.d("MainActivity", "Username passed: " + username);


                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                Intent mainIntent = new Intent(this, MainPage.class);
                mainIntent.putExtra("username", etus.getText().toString());

                startActivity(mainIntent);

            } else {

                Toast.makeText(this, "Invalid username and/or password", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {

            Toast.makeText(this, "Database error", Toast.LENGTH_SHORT).show();
        }

    }



    public void onNewacc(View v) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}