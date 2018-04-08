package com.example.herman.mobilekitchen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBProductsHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "productsDb";

    public static final String TABLE_PRODUCTS = "products";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_MEASURE = "measure";
    public static final String KEY_EXP_DATE = "exp_date";
    public static final String KEY_LABEL = "label";

    public static final String TABLE_LABELS = "labels";

    public static final String KEY_ICON = "icon";
    public static final String KEY_CATEGORY = "category";


    public DBProductsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PRODUCTS + "("
                + KEY_ID + " integer primary key,"
                + KEY_NAME + " text,"
                + KEY_AMOUNT + " integer,"
                + KEY_MEASURE + " text,"
                + KEY_EXP_DATE + " integer,"
                + KEY_LABEL + " text,"
                + "foreign key (" + KEY_LABEL + ") references " + TABLE_LABELS + "(" + KEY_LABEL + ")"
                + ")");

        db.execSQL("create table " + TABLE_LABELS + "("
                + KEY_LABEL + " text primary key,"
                + KEY_ICON + " text,"
                + KEY_CATEGORY + " text"
                + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PRODUCTS);
        db.execSQL("drop table if exists " + TABLE_LABELS);

        onCreate(db);

    }
}