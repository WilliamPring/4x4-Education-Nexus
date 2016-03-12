package com.example.administrator.newfocalpoint;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by WilliamPring on 3/12/2016.
 */
public class AccountListDB {
    public static final String DB_NAME = "InformationLogin.db";
    public static final int DB_VERSION = 1;
    //list table consants
    public static final String TableName = "Account";
    public static String NAME = "Name";
    public static final String TASK_ID = "_id";
    public static String LastName = "Last_Name";
    public static String Email = "Email";
    public static String DOB = "Date_Of_Birth";
    public static String School = "School";
    public static String Password = "Password";
    public static final String CREATE_ACCOUNT_TABLE =
            "CREATE TABLE " + TableName + " (" +
                    TASK_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    LastName + " TEXT NOT NULL, " +
                    Email + " TEXT NOT NULL, " +
                    DOB + " TEXT NOT NULL, " +
                    School + " TEXT NOT NULL, " +
                    Password + " TEXT NOT NULL);";

    public static final String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS" + TableName;
    private static class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_ACCOUNT_TABLE);
        }



        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            db.execSQL(AccountListDB.DROP_LIST_TABLE);
            onCreate(db);
        }
    }
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public AccountListDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }
    private void closeDB() {
        if (db != null)
            db.close();
    }
    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }
    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    public boolean matchPasswordAndUser(String user, String pass)
    {
        boolean status = false;
        openReadableDB();
        ContentValues contentValues = new ContentValues();
        String query = "SELECT Email, Password FROM " + TableName +";";
        Cursor cursor = db.rawQuery(query, null);
        String username;
        String password;
        if (cursor.moveToFirst())
        {
            do {
                username = cursor.getString(0);
                password = cursor.getString(1);
                if ((username.equals(user)) && (password.equals(pass)))
                {
                    status = true;
                    break;
                }

            }while(cursor.moveToNext());
        }
        closeCursor(cursor);
        return status;
    }
    public long insertTask(Account account) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, account.getNewName());
        cv.put(LastName, account.getNewLastName());
        cv.put(Email, account.getNewEmail());
        cv.put(DOB, account.getNewDOB());
        cv.put(School, account.getNewSchool());
        cv.put(Password, account.getNewTextPassword());

        this.openWriteableDB();
        long rowID = db.insert(TableName, null, cv);

        this.closeDB();

        return rowID;
    }

}
