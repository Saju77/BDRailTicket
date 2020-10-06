package com.example.bdrailticket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bdrailticket.model.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bdrailticket";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String USER_ID = "id";
    public static final String USER_F_NAME = "full_name";
    public static final String USER_MOBILE = "mobile";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_C_PASSWORD = "confirm_password";

    private static final String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+ "(" + USER_ID + " INTEGER PRIMARY KEY," + USER_F_NAME
            + " TEXT," + USER_MOBILE + " INTEGER," + USER_EMAIL + " TEXT," + USER_PASSWORD + " TEXT," + USER_C_PASSWORD + " TEXT" + ")";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    public long addUser(User user){

        ContentValues values = new ContentValues();
        values.put(USER_F_NAME, user.getFull_name());
        values.put(USER_MOBILE, user.getMobile());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_C_PASSWORD, user.getConfirm_password());

        SQLiteDatabase database = this.getWritableDatabase();
        long i = database.insert(TABLE_USER,null, values);
        Log.d("DBHelper",String.valueOf(i));

        return i;
    }

    public User login(User user){
        String sql = "Select * from "+TABLE_USER+" where "+USER_MOBILE+" = ? AND "+USER_PASSWORD+" =?";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(user.getMobile()),user.getPassword()});

        if(cursor.moveToFirst()){
            Log.d("Login", cursor.getString(1));
            User user1 = new User();
            user1.setFull_name(cursor.getString(1));
            user1.setMobile(cursor.getInt(2));
            user1.setEmail(cursor.getString(3));
            user1.setPassword(cursor.getString(4));
            return user1;
        }else{
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
