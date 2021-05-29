package com.cap0394.sahabattani.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.Tengkulak;

import java.util.ArrayList;

public class CardViewTengkulakAdapter extends RecyclerView.Adapter<CardViewTengkulakAdapter.CardViewHolder> {
    private ArrayList<Tengkulak> listTengkulak;

    public CardViewTengkulakAdapter(ArrayList<Tengkulak> list) {
        this.listTengkulak = list;
    }
    @NonNull
    @Override
    public CardViewTengkulakAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_tengkulak, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTengkulakAdapter.CardViewHolder holder, int position) {
        Tengkulak tengkulak = listTengkulak.get(position);
        /*Glide.with(holder.itemView.getContext())
                .load(product.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);*/
        holder.tvTengkulakName.setText(tengkulak.getName());
    }

    @Override
    public int getItemCount() {
        return listTengkulak.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        // ImageView imgPhoto;
        TextView tvTengkulakName;
        CardViewHolder(View itemView) {
            super(itemView);
            tvTengkulakName = itemView.findViewById(R.id.name_tengkulak);
        }
    }
}
