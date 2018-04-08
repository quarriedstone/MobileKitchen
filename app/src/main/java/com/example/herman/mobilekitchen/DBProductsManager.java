package com.example.herman.mobilekitchen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBProductsManager {

    private DBProductsHelper dbProductsHelper;
    private SQLiteDatabase database;

    public DBProductsManager(Context context) {

        dbProductsHelper= new DBProductsHelper(context);


        fillLabelsTabel();
    }

    private void fillLabelsTabel() {
        //TODO: insert some initial entries
    }

    public void addProduct(String name, int amount, String measure, int exp_date, String label) {

        database = dbProductsHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBProductsHelper.KEY_NAME, name);
        contentValues.put(DBProductsHelper.KEY_AMOUNT, amount);
        contentValues.put(DBProductsHelper.KEY_MEASURE, measure);
        contentValues.put(DBProductsHelper.KEY_EXP_DATE, exp_date);
        contentValues.put(DBProductsHelper.KEY_LABEL, label);

        database.insert(DBProductsHelper.TABLE_PRODUCTS, null, contentValues);

        database.close();
    }

    public Cursor getProducts() {
        database = dbProductsHelper.getWritableDatabase();

        String sqlQuery = "select * "
                + "from " + DBProductsHelper.TABLE_PRODUCTS + " as P "
                + "inner join " + DBProductsHelper.TABLE_LABELS + " as L "
                + "on P." + DBProductsHelper.KEY_LABEL + " = L." + DBProductsHelper.KEY_LABEL;

        Cursor cursor = database.rawQuery(sqlQuery, null);
        database.close();
        cursor.close(); //IF DOES NOT WORK THEN DELETE
        return cursor;
    }

    public int deleteProduct(int id) {
        database = dbProductsHelper.getWritableDatabase();
        int count = database.delete(DBProductsHelper.TABLE_PRODUCTS, "id = " + id, null);
        database.close();
       return count;
    }

    public int updateProduct(Integer id, String name, Integer amount, String measure, Integer exp_date, String label){
        database = dbProductsHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (name != null) {
            contentValues.put(DBProductsHelper.TABLE_PRODUCTS, name);
        }

        if (amount != null) {
            contentValues.put(DBProductsHelper.TABLE_PRODUCTS, amount);
        }

        if (measure != null) {
            contentValues.put(DBProductsHelper.TABLE_PRODUCTS, measure);
        }

        if (exp_date != null) {
            contentValues.put(DBProductsHelper.TABLE_PRODUCTS, exp_date);
        }

        if (label != null) {
            contentValues.put(DBProductsHelper.TABLE_PRODUCTS, label);
        }
        int count = database.update(DBProductsHelper.TABLE_PRODUCTS, contentValues,
                "id = ?", new String[] { id.toString() });
        database.close();
        return count;
    }

}
