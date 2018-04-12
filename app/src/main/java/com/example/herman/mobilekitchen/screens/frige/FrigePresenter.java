package com.example.herman.mobilekitchen.screens.frige;

import android.content.Context;

import com.example.herman.mobilekitchen.database.DBProductsManager;
import com.example.herman.mobilekitchen.interfaces.IUpdateData;

import java.util.List;

public class FrigePresenter {

    private IUpdateData iUpdateData;
    private Context context;
    private DBProductsManager helper;


    public FrigePresenter() {
    }

    public FrigePresenter(IUpdateData iUpdateData, Context context) {
        this.iUpdateData = iUpdateData;
        this.context = context;
    }

    public void addProduct(ProductModel model) {
        helper = new DBProductsManager(context);
        helper.addNewProduct(model);
        updateProductsAdapter();
    }

    public void updateProductsAdapter() {
        helper = new DBProductsManager(context);
        iUpdateData.updateFrige(helper.getAllProducts());
    }

    public List<ProductModel> getProducts() {
        helper = new DBProductsManager(context);
        return helper.getAllProducts();
    }

    public void deleteProduct(ProductModel model) {
        helper = new DBProductsManager(context);
        helper.deleteProduct(model);
        iUpdateData.updateFrige(getProducts());
    }
}
