package com.example.appdevfinalproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // database name
    private static final String DB_NAME = "Users.db";
    private static final int DB_VERSION = 2;

    //represent columns
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DATEOFBIRTH = "dateofbirth";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONENUMBER = "phonenumber";

    public static final String TABLE_CART = "cart";
    public static final String COLUMN_CART_USERNAME = "cart_username";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_PRODUCT_IMAGE = "product_image";
     //Constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table for login and registration
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, " +
                COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                COLUMN_DATEOFBIRTH + " TEXT, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PHONENUMBER + " TEXT DEFAULT '')";
        db.execSQL(createUserTableQuery);

        // table for item or product
        String createCartTableQuery = "CREATE TABLE " + TABLE_CART + " (" +
                COLUMN_CART_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                COLUMN_PRODUCT_PRICE + " REAL NOT NULL, " +
                COLUMN_PRODUCT_IMAGE + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_CART_USERNAME + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USERNAME + "))";
        db.execSQL(createCartTableQuery);
    }

    @Override
    //handle changes
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // for adding to cart
    public void addToCart(String username, String productName, double productPrice, int productImage) {
        SQLiteDatabase db = this.getWritableDatabase();

//retrieve rows
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CART + " WHERE " + COLUMN_CART_USERNAME + " = ? AND " + COLUMN_PRODUCT_NAME + " = ?",
                new String[]{username, productName}
        );
// check if it already exists
        if (!cursor.moveToFirst()) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_CART_USERNAME, username);
            values.put(COLUMN_PRODUCT_NAME, productName);
            values.put(COLUMN_PRODUCT_PRICE, productPrice);
            values.put(COLUMN_PRODUCT_IMAGE, productImage);
            db.insert(TABLE_CART, null, values);
        }

        cursor.close();
        db.close();
    }

    //retrieve items on cart
    public List<CartItem> getCartItems(String username) {
        List<CartItem> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CART + " WHERE " + COLUMN_CART_USERNAME + " = ?",
                new String[]{username}
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") int productPrice = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));
                @SuppressLint("Range") int productImage = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE));

                cartItems.add(new CartItem( username,productName, productPrice, productImage));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cartItems;
    }
    //for user profile
    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?",
                new String[]{username}
        );
    }
    //delete user
    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS, COLUMN_USERNAME + "=?", new String[]{username}) > 0;
    }
    //edit user information
    public boolean updateUserProfile(String username, String email, String dateOfBirth, String address, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_DATEOFBIRTH, dateOfBirth);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_PHONENUMBER, phoneNumber);

        return db.update(TABLE_USERS, values, COLUMN_USERNAME + "=?", new String[]{username}) > 0;
    }

}



