package com.example.herman.mobilekitchen.screens.frige;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.herman.mobilekitchen.R;
import com.example.herman.mobilekitchen.adapters.FrigeAdapter;
import com.example.herman.mobilekitchen.interfaces.IUpdateData;

import java.util.ArrayList;
import java.util.List;

public class FrigeFragment extends Fragment implements IUpdateData {

    RecyclerView rv;
    FrigeAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<ProductModel> list = new ArrayList<>();
    FrigePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frigefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FrigePresenter(this, getContext());

        List<ProductModel> result = presenter.getProducts();

        rv = view.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getContext(), 3);
        rv.setLayoutManager(layoutManager);
        adapter = new FrigeAdapter(result);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mockFillDB();
    }

    private void mockFillDB() {
        ProductModel productModel = new ProductModel(R.drawable.apple, "Apple",  System.currentTimeMillis(), 2, "kilos", "somethis");
        ProductModel productModel2 = new ProductModel(R.drawable.salami, "Meat",  System.currentTimeMillis(), 2, "kilos", "somethis");
        ProductModel productModel3 = new ProductModel(R.drawable.eggs, "Eggs",  System.currentTimeMillis(), 2, "kilos", "somethis");
        presenter.addProduct(productModel);
        presenter.addProduct(productModel2);
        presenter.addProduct(productModel3);

    }

    @Override
    public void updateFrige(List<ProductModel> list) {
        adapter.update(list);
    }

    public void remove() {
        List<ProductModel> models;
        models = presenter.getProducts();
        presenter.deleteProduct(models.get(0));
    }
}
