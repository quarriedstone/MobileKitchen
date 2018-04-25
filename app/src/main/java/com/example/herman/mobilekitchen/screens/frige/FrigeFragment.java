package com.example.herman.mobilekitchen.screens.frige;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.herman.mobilekitchen.R;
import com.example.herman.mobilekitchen.adapters.ConnectorDrawable;
import com.example.herman.mobilekitchen.adapters.FrigeAdapter;
import com.example.herman.mobilekitchen.interfaces.IShowDialog;
import com.example.herman.mobilekitchen.interfaces.IUpdateData;

import java.util.ArrayList;
import java.util.List;

public class FrigeFragment extends Fragment implements IUpdateData, IShowDialog {

    RecyclerView rv;
    FrigeAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<ProductModel> list = new ArrayList<>();
    FrigePresenter presenter;
    AlertDialog ad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frigefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FrigePresenter(this, getContext(), this);
        intialize(view);
    }

    private void intialize(View view) {
        FloatingActionButton deleteProductButton = (FloatingActionButton) view.findViewById(R.id.deleteProductButton);
        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Chose item to delete or press again if you won't", Toast.LENGTH_LONG).show();
                presenter.setNextFoodMustBeDeleted(!presenter.isNextFoodMustBeDeleted());
            }
        });

        /*
        for (ProductModel m:presenter.getProducts()
             ) {
            remove();
        }*/

        List<ProductModel> result = presenter.getProducts();
        rv = (RecyclerView) view.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getContext(), 3);
        rv.setLayoutManager(layoutManager);
        adapter = new FrigeAdapter(result, presenter);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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

    @Override
    public void showDialog(final ProductModel model) {
        View relativeLayout = getLayoutInflater().inflate(R.layout.friegeitem, null);
        ad = new AlertDialog.Builder(getContext()).create();
        ad.setView(relativeLayout);
        final EditText name = relativeLayout.findViewById(R.id.name);
        final EditText weight = relativeLayout.findViewById(R.id.weight);
        final EditText measure = relativeLayout.findViewById(R.id.measure);
        final EditText expdate = relativeLayout.findViewById(R.id.expdate);
        Button close = relativeLayout.findViewById(R.id.close);
        Button save = relativeLayout.findViewById(R.id.save);
        name.setText(model.getProdName());
        weight.setText(String.valueOf(model.getAmount()));
        measure.setText(model.getMeasure());

        expdate.setText(model.getExpireDate());
        ad.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setProdName(name.getText().toString());
                model.setAmount(Long.parseLong(weight.getText().toString()));
                model.setMeasure(measure.getText().toString());
                model.setExpireDate(expdate.getText().toString());
                presenter.updateProductInfo(model);
                ad.dismiss();
            }
        });
    }
}
