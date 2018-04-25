package com.example.herman.mobilekitchen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.herman.mobilekitchen.R;
import com.example.herman.mobilekitchen.screens.frige.FrigePresenter;
import com.example.herman.mobilekitchen.screens.frige.ProductModel;

import java.util.List;

public class FrigeAdapter extends RecyclerView.Adapter<FrigeAdapter.ViewHolder> {

    private List<ProductModel> list;
    private Context context;
    private FrigePresenter presenter;

    public FrigeAdapter(List<ProductModel> list, FrigePresenter presenter) {
        this.list = list;
        this.presenter = presenter;
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
        holder.prodPicture.setImageResource(list.get(position).getDrawable());
        holder.prodPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.isNextFoodMustBeDeleted()) {
                    presenter.deleteProduct(list.get(position));
                    presenter.setNextFoodMustBeDeleted(false);
                } else
                    presenter.getProduct(list.get(position).getId());
            }
        });

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
