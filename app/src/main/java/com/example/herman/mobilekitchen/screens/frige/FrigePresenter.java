package com.example.herman.mobilekitchen.screens.frige;

import android.content.Context;
import android.util.Log;

import com.example.herman.mobilekitchen.database.DBProductsManager;
import com.example.herman.mobilekitchen.interfaces.IShowDialog;
import com.example.herman.mobilekitchen.interfaces.IUpdateData;

import java.util.List;

public class FrigePresenter {

    private IUpdateData iUpdateData;
    private Context context;
    private DBProductsManager helper;
    private IShowDialog iShowDialog;
    private boolean nextFoodMustBeDeleted;

    public FrigePresenter(Context context) {
        this.context = context;
    }

    public FrigePresenter(IUpdateData iUpdateData, Context context, IShowDialog iShowDialog) {
        this.iUpdateData = iUpdateData;
        this.iShowDialog = iShowDialog;
        this.context = context;
    }

    /**
     * Для работы без instantUpdate нужен только контекст
     * С instantUpdate нужны интерфейсы. Без передачи интерфейсов в конструктов будет NPE
     *
     * @param model
     * @param instantUpdate
     */
    public void addProduct(ProductModel model, boolean instantUpdate) {
        helper = new DBProductsManager(context);
        helper.addNewProduct(model);
        if (instantUpdate)
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

    public void getProduct(int prodId) {
        helper = new DBProductsManager(context);
        iShowDialog.showDialog(helper.getOneProduct(prodId));
    }

    public void deleteProduct(ProductModel model) {
        helper = new DBProductsManager(context);
        helper.deleteProduct(model);
        iUpdateData.updateFrige(getProducts());
    }

    public void updateProductInfo(ProductModel model) {
        helper = new DBProductsManager(context);
        helper.updateProduct(model);
        iUpdateData.updateFrige(getProducts());
    }

    public void setNextFoodMustBeDeleted(boolean nextFoodMustBeDeleted) {
        this.nextFoodMustBeDeleted = nextFoodMustBeDeleted;
    }

    public boolean isNextFoodMustBeDeleted() {
        return nextFoodMustBeDeleted;
    }
}
