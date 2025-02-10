package com.vrushabh.assignment_member_registration.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemberDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "members.db";
    private static final int DATABASE_VERSION = 1;

    public MemberDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE members (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "mobileNumber TEXT," +
                "name TEXT," +
                "role TEXT," +
                "subscriptionFee REAL," +
                "depositAmount REAL," +
                "loanAmount REAL," +
                "gender TEXT," +
                "dob TEXT," +
                "maritalStatus TEXT," +
                "joiningDate TEXT," +
                "marriageDate TEXT," +
                "caste TEXT," +
                "category TEXT," +
                "aadharNumber TEXT," +
                "religion TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS members");
        onCreate(db);
    }
}
