package com.example.herman.mobilekitchen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.herman.mobilekitchen.screens.frige.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class DBProductsManager {

    private DBProductsHelper dbProductsHelper;
    private SQLiteDatabase database;
    Context context;

    public DBProductsManager(Context context) {
        this.context = context;
        fillLabelsTabel();
    }

    private void fillLabelsTabel() {
        //TODO: insert some initial entries
    }

    public int deleteProduct(ProductModel model) {
        dbProductsHelper = new DBProductsHelper(context);
        SQLiteDatabase database = dbProductsHelper.getWritableDatabase();
        database.beginTransaction();
        int count = database.delete(DBProductsHelper.TABLE_PRODUCTS, DBProductsHelper.KEY_ID + " = " + model.getId(), null);
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
        return count;
    }

    public void addNewProduct(ProductModel model) {
        dbProductsHelper = new DBProductsHelper(context);
        SQLiteDatabase database = dbProductsHelper.getWritableDatabase();
        database.beginTransaction();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBProductsHelper.KEY_NAME, model.getProdName());
        contentValues.put(DBProductsHelper.KEY_AMOUNT, model.getAmount());
        contentValues.put(DBProductsHelper.KEY_EXP_DATE, model.getExpireDate());
        contentValues.put(DBProductsHelper.KEY_MEASURE, model.getMeasure());
        contentValues.put(DBProductsHelper.KEY_IMAGE, model.getDrawable());
        contentValues.put(DBProductsHelper.KEY_TYPE, model.getType());

        database.insert(DBProductsHelper.TABLE_PRODUCTS, null, contentValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
        dbProductsHelper.close();
    }

    public List<ProductModel> getAllProducts() {
        dbProductsHelper = new DBProductsHelper(context);
        SQLiteDatabase database = dbProductsHelper.getReadableDatabase();

        String sqlQuery = "select * "
                + "from " + DBProductsHelper.TABLE_PRODUCTS + " as P "
                + "inner join " + DBProductsHelper.TABLE_LABELS + " as L "
                + "on P." + DBProductsHelper.KEY_TYPE + " = L." + DBProductsHelper.KEY_TYPE;

        sqlQuery = "SELECT * FROM " + DBProductsHelper.TABLE_PRODUCTS;
        //TODO переделать запрос на тот, что стоит выше, когда появятся типы продуктов
        Cursor cursor = database.rawQuery(sqlQuery, null);

        List<ProductModel> result = new ArrayList<>();
        ProductModel model;
        while (cursor.moveToNext()) {
            model = new ProductModel();
            int indexId = cursor.getColumnIndex(DBProductsHelper.KEY_ID);
            int indexProdName = cursor.getColumnIndex(DBProductsHelper.KEY_NAME);
            int indexAmount = cursor.getColumnIndex(DBProductsHelper.KEY_AMOUNT);
            int indexExpireDate = cursor.getColumnIndex(DBProductsHelper.KEY_EXP_DATE);
            int indexMeasure = cursor.getColumnIndex(DBProductsHelper.KEY_MEASURE);
            int indexDrawable = cursor.getColumnIndex(DBProductsHelper.KEY_IMAGE);
            int indexType = cursor.getColumnIndex(DBProductsHelper.KEY_TYPE);

            model.setId(cursor.getInt(indexId));
            model.setAmount(cursor.getInt(indexAmount));
            model.setDrawable(cursor.getInt(indexDrawable));
            model.setExpireDate(cursor.getLong(indexExpireDate));
            model.setMeasure(cursor.getString(indexMeasure));
            model.setProdName(cursor.getString(indexProdName));
            model.setType(cursor.getString(indexType));
            result.add(model);
            Log.e("READ", model.getProdName());
        }

        cursor.close(); //IF DOES NOT WORK THEN DELETE
        database.close();
        return result;
    }

    public int updateProduct(ProductModel model) {
        dbProductsHelper = new DBProductsHelper(context);
        SQLiteDatabase database = dbProductsHelper.getWritableDatabase();
        database.beginTransaction();
        ContentValues contentValues = new ContentValues();
        if (model.getProdName() != null) {
            contentValues.put(DBProductsHelper.KEY_NAME, model.getProdName());
        }

        if (String.valueOf(model.getAmount()).equals("null")) {
            contentValues.put(DBProductsHelper.KEY_AMOUNT, model.getAmount());
        }

        if (model.getMeasure() != null) {
            contentValues.put(DBProductsHelper.KEY_MEASURE, model.getMeasure());
        }

        if (String.valueOf(model.getExpireDate()).equals("null")) {
            contentValues.put(DBProductsHelper.KEY_EXP_DATE, model.getExpireDate());
        }

        if (String.valueOf(model.getDrawable()).equals("null")) {
            contentValues.put(DBProductsHelper.KEY_IMAGE, model.getDrawable());
        }
        if (String.valueOf(model.getMass()).equals("null")) {
            contentValues.put(DBProductsHelper.KEY_MASS, model.getMass());
        }
        if (model.getType() != null) {
            contentValues.put(DBProductsHelper.KEY_TYPE, model.getType());
        }
        int count = database.update(DBProductsHelper.TABLE_PRODUCTS, contentValues,
                "id = ?", new String[]{String.valueOf(model.getId())});
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
        return count;
    }
}
