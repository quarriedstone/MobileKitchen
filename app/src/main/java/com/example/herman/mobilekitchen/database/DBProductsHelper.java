package com.example.herman.mobilekitchen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBProductsHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "productsDb";

    public static final String TABLE_PRODUCTS = "products";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_MEASURE = "measure";
    public static final String KEY_EXP_DATE = "exp_date";
    public static final String KEY_IMAGE = "picture";
    public static final String KEY_TYPE = "type";

    //public static final String TABLE_LABELS = "labels";

    public DBProductsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_PRODUCTS + "("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " text,"
                + KEY_AMOUNT + " float,"
                + KEY_MEASURE + " text,"
                + KEY_EXP_DATE + " bigint,"
                + KEY_IMAGE + " bigint,"
                + KEY_TYPE + " integer"
                + ")");

//          + "foreign key (" + KEY_TYPE + ") references " + TABLE_LABELS + "(" + KEY_TYPE + ")"
//
//        db.execSQL("create table if not exists " + TABLE_LABELS + "("
//                + KEY_ID + " integer primary key autoincrement, "
//                + KEY_TYPE + " text"
//                + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PRODUCTS);
//        db.execSQL("drop table if exists " + TABLE_LABELS);

        onCreate(db);

    }
}