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
import com.cap0394.sahabattani.InfoPertanian;
import com.cap0394.sahabattani.R;

import java.util.ArrayList;

public class CardViewInfoPertanianAdapter extends RecyclerView.Adapter<CardViewInfoPertanianAdapter.CardViewHolder> {

    private ArrayList<InfoPertanian> listInfo;

    public CardViewInfoPertanianAdapter(ArrayList<InfoPertanian> list) {
        this.listInfo = list;
    }
    @NonNull
    @Override
    public CardViewInfoPertanianAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_info_pertanian, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewInfoPertanianAdapter.CardViewHolder holder, int position) {
        InfoPertanian post = listInfo.get(position);
        /*Glide.with(holder.itemView.getContext())
                .load(post.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);*/
        holder.tvTitle.setText(post.getTitle());
        holder.tvPostBody.setText(post.getPostBody());
    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        // ImageView imgPhoto;
        TextView tvTitle, tvPostBody;
        CardViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_post);
            tvPostBody = itemView.findViewById(R.id.txt_post);
        }
    }
}
