package com.example.herman.mobilekitchen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.herman.mobilekitchen.R;
import com.example.herman.mobilekitchen.screens.frige.ProductModel;

import java.util.List;

public class FrigeAdapter extends RecyclerView.Adapter<FrigeAdapter.ViewHolder> {

    private List<ProductModel> list;
    private Context context;

    public FrigeAdapter(List<ProductModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.prodName.setText(list.get(position).getProdName());
//        Picasso.with(context).load(list.get(position).getDrawable()).into(holder.prodPicture);
//        Log.e("DRAWABLE", String.valueOf(list.get(position).getDrawable()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prodPicture;
        TextView prodName;

        ViewHolder(View view) {
            super(view);
            prodPicture = view.findViewById(R.id.prodPicture);
            prodName = view.findViewById(R.id.prodName);

        }
    }

    public void update(List<ProductModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
}
