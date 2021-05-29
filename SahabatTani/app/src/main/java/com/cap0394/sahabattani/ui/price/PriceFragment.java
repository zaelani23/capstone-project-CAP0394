package com.cap0394.sahabattani.ui.price;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cap0394.sahabattani.adapter.CardViewCommodityAdapter;
import com.cap0394.sahabattani.Commodity;
import com.cap0394.sahabattani.CommodityData;
import com.cap0394.sahabattani.R;

import java.util.ArrayList;

public class PriceFragment extends Fragment {

    private PriceViewModel priceViewModel;
    private RecyclerView rvCommodity;
    private ArrayList<Commodity> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        priceViewModel = new ViewModelProvider(this).get(PriceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_price, container, false);
        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        priceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        list.addAll(CommodityData.getListData());

        rvCommodity = view.findViewById(R.id.rv_commodities);
        rvCommodity.setHasFixedSize(true);
        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvCommodity.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewCommodityAdapter cardViewPriceAdapter = new CardViewCommodityAdapter(list);
        rvCommodity.setAdapter(cardViewPriceAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cek_harga, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}