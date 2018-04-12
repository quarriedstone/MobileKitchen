package com.example.herman.mobilekitchen.screens.frige;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.herman.mobilekitchen.R;

public class ProductFragment extends Fragment {

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
     * This starts when all view elemnts are loaded
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
    }
}
