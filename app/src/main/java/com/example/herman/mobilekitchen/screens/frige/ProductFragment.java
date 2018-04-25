package com.example.herman.mobilekitchen.screens.frige;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.herman.mobilekitchen.R;
import com.example.herman.mobilekitchen.adapters.ConnectorDrawable;

public class ProductFragment extends Fragment {
    final ConnectorDrawable conn = new ConnectorDrawable();

    /**
     * This starts when fragment loads
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.foodfragment, container, false);
    }

    /**
     * This starts when all view elements are loaded
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initElements(view);
    }

    private void initElements(View view) {
        final EditText name = view.findViewById(R.id.name);
        final EditText weight = view.findViewById(R.id.weight);
        final EditText measure = view.findViewById(R.id.measure);
        final EditText expdate = view.findViewById(R.id.expdate);
        final EditText label = view.findViewById(R.id.label);
        final Button save = view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrigePresenter presenter = new FrigePresenter(getContext());
                ProductModel model = new ProductModel();

                model.setProdName(name.getText().toString());
                model.setAmount(Long.parseLong(weight.getText().toString()));
                model.setMeasure(measure.getText().toString());
                model.setExpireDate(expdate.getText().toString());
                model.setDrawable(conn.getDrawableId(label.getText().toString()));

                presenter.addProduct(model, false);
                ((FrigeActivity) getActivity()).changeFragment(new FrigeFragment(), false);
            }
        });
    }
}
