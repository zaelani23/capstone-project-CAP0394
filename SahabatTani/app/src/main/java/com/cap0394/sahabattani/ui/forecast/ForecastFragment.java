package com.cap0394.sahabattani.ui.forecast;

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

import com.cap0394.sahabattani.adapter.CardViewForecastAdapter;
import com.cap0394.sahabattani.Commodity;
import com.cap0394.sahabattani.CommodityData;
import com.cap0394.sahabattani.R;

import java.util.ArrayList;

public class ForecastFragment extends Fragment {

    private ForecastViewModel forecastViewModel;
    private RecyclerView rvForecast;
    private ArrayList<Commodity> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        forecastViewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forecast, container, false);
        /*final TextView textView = root.findViewById(R.id.text_forecast);
        forecastViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        rvForecast = view.findViewById(R.id.rv_forecast_price);
        rvForecast.setHasFixedSize(true);
        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvForecast.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewForecastAdapter cardViewForecastAdapter = new CardViewForecastAdapter(list);
        rvForecast.setAdapter(cardViewForecastAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_date, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}