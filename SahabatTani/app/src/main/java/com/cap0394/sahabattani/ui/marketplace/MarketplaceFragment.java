package com.cap0394.sahabattani.ui.marketplace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cap0394.sahabattani.Marketplace;
import com.cap0394.sahabattani.MarketplaceData;
import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.adapter.CardViewMarketplaceAdapter;

import java.util.ArrayList;

public class MarketplaceFragment extends Fragment {

    private MarketplaceViewModel marketplaceViewModel;
    private RecyclerView rvMarketplace;
    private ArrayList<Marketplace> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        marketplaceViewModel = new ViewModelProvider(this).get(MarketplaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marketplace, container, false);
        /*final TextView textView = root.findViewById(R.id.text_marketplace);
        marketplaceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list.addAll(MarketplaceData.getListData());

        rvMarketplace = view.findViewById(R.id.rv_marketplace);
        rvMarketplace.setHasFixedSize(true);
        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvMarketplace.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewMarketplaceAdapter cardViewInfoPertanianAdapter = new CardViewMarketplaceAdapter(list);
        rvMarketplace.setAdapter(cardViewInfoPertanianAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_appbar_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}