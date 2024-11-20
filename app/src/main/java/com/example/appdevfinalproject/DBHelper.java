package com.example.appdevfinalproject;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressWarnings("unused")
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_USERS = "users";



    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DATEOFBIRTH = "dateofbirth";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONENUMBER = "phonenumber";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +

                COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, " +
                COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                COLUMN_DATEOFBIRTH + " TEXT, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PHONENUMBER + " TEXT DEFAULT ''" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
