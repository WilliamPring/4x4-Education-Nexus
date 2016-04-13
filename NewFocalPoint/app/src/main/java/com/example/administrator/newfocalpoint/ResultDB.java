package com.example.administrator.newfocalpoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by William Pring on 4/13/2016.
 */
public class ResultDB {
    public static final String DB_NAME = "ResultsInformation.db";
    public static final int DB_VERSION = 1;
    //list table consants
    public static final String TableName = "Results";
    public static final String TASK_ID = "_id";
    public static String Question = "Question_Number";
    public static String QuestionAns = "Question_Answer";
    public static String UserAnswer = "User_Answer";

    public static final String CREATE_ACCOUNT_TABLE =
            "CREATE TABLE " + TableName + " (" +
                    TASK_ID         + " INTEGER PRIMARY KEY, " +
                    Question + " TEXT NOT NULL, " +
                    QuestionAns + " TEXT NOT NULL, " +
                    UserAnswer + " TEXT NOT NULL);";

    public static final String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS" + TableName;
    private static class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public ResultDB(Context context) {
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


    public long insertTask(Results results) {
        ContentValues cv = new ContentValues();
        cv.put(TASK_ID, results.getId());
        cv.put(Question, results.getQuestionNumber());
        cv.put(QuestionAns, results.getQuestionAnswer());
        cv.put(UserAnswer, results.getUserChoice());
        this.openWriteableDB();
        long rowID = db.insert(TableName, null, cv);

        this.closeDB();

        return rowID;
    }

}
