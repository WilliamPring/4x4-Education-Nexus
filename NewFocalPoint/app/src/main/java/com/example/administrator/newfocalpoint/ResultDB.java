package com.example.administrator.newfocalpoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

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
    public static String UserAcc = "userAcc";
    public static final String CREATE_ACCOUNT_TABLE =
            "CREATE TABLE " + TableName + " (" +
                    TASK_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Question + " INTEGER, " +
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

    public List<Results> GetResultsList()
    {
        List<Results> myResult = new ArrayList<>();
        boolean status = false;
        openReadableDB();
        ContentValues contentValues = new ContentValues();
        String query = "SELECT Question_Number, Question_Answer, User_Answer FROM " + TableName +";";
        Cursor cursor = db.rawQuery(query, null);
        int question;
        String userAnswer;
        String questionAnswer;
        if (cursor.moveToFirst()) {
            try {
                do {
                    question = cursor.getInt(0);
                    questionAnswer = cursor.getString(1);
                    userAnswer = cursor.getString(2);
                    myResult.add(new Results(LoginFragment.ID, question, questionAnswer, userAnswer));

                } while (cursor.moveToNext());
            } catch (Exception ex) {
                String info = ex.toString();
            }
        }
        closeCursor(cursor);
        return myResult;
    }

    public long insertTask(Results results) {
        ContentValues cv = new ContentValues();
        cv.put(Question, results.getQuestionNumber());
        cv.put(QuestionAns, results.getQuestionAnswer());
        cv.put(UserAnswer, results.getUserChoice());
        this.openWriteableDB();
        long rowID = db.insert(TableName, null, cv);

        this.closeDB();

        return rowID;
    }

}
