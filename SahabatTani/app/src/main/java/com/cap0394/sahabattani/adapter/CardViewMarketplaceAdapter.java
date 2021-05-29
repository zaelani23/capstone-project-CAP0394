package com.cap0394.sahabattani.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cap0394.sahabattani.Marketplace;
import com.cap0394.sahabattani.R;

import java.util.ArrayList;

public class CardViewMarketplaceAdapter extends RecyclerView.Adapter<CardViewMarketplaceAdapter.CardViewHolder> {
    private ArrayList<Marketplace> listProduct;

    public CardViewMarketplaceAdapter(ArrayList<Marketplace> list) {
        this.listProduct = list;
    }
    @NonNull
    @Override
    public CardViewMarketplaceAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_marketplace, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewMarketplaceAdapter.CardViewHolder holder, int position) {
        Marketplace product = listProduct.get(position);
        /*Glide.with(holder.itemView.getContext())
                .load(product.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);*/
        holder.tvProductTitle.setText(product.getProductTitle());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        // ImageView imgPhoto;
        TextView tvProductTitle;
        CardViewHolder(View itemView) {
            super(itemView);
            tvProductTitle = itemView.findViewById(R.id.title_product);
        }
    }
}
