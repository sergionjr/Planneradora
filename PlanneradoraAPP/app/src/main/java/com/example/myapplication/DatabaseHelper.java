package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_DATA";
    public static final String COLUMN_USERNAME = "USER_USERNAME";
    public static final String COLUMN_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "UserData.db", null, 1);
    }

    // Sergio: This is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);
    }

    // Sergio: This is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Sergio: This function will register a new user
    public boolean registerUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Sergio: This uses the userModel.getUserName()/getPassword() functions to pull the text box contents and send them to SQL.
        cv.put(COLUMN_USERNAME, userModel.getUserName());
        cv.put(COLUMN_PASSWORD, userModel.getPassWord());

        // Sergio: db.insert(); will send the parameters to the SQL database. By making insert a long, it will return a -1 (false) if
        // the data failed to submit to the database. It will return true if it went through.
        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    // Sergio: This function will sign the user in by grabbing both the username and password from the SQL
    // database and comparing it to the entered fields
    public boolean signInUser(UserModel userModel){
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString1 = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " = " + userModel.getUserName();
        String queryString2 = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " = " + userModel.getUserName() + " AND "
                + COLUMN_PASSWORD + " = " + userModel.getPassWord();

        Cursor cursor = db.rawQuery("Select * from USER_DATA where USER_USERNAME = ?", new String[]{userModel.getUserName()});
        return cursor.getColumnCount() > -1 ? false : true;

//        int userID = cursor.getInt(0);
//        String userUsername = cursor.getString(1);
//        String userPassword = cursor.getString(2);
//
//        UserModel queriedModel = new UserModel(userID, userUsername, userPassword);
//        return queriedModel;


    }
}
