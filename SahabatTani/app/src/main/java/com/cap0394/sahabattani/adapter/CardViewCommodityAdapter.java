package com.cap0394.sahabattani.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cap0394.sahabattani.Commodity;
import com.cap0394.sahabattani.R;

import java.util.ArrayList;

public class CardViewCommodityAdapter extends RecyclerView.Adapter<CardViewCommodityAdapter.CardViewHolder> {

    private ArrayList<Commodity> listCommodity;

    public CardViewCommodityAdapter(ArrayList<Commodity> list) {
        this.listCommodity = list;
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_commodity_price, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Commodity commodity = listCommodity.get(position);
        Glide.with(holder.itemView.getContext())
                .load(commodity.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.tvName.setText(commodity.getName());
    }

    @Override
    public int getItemCount() {
        return listCommodity.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;
        CardViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.title_commodity);
        }
    }
}
