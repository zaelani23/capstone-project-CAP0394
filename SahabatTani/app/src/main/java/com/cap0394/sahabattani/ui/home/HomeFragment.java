package com.cap0394.sahabattani.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.activity.KonsultasiActivity;
import com.cap0394.sahabattani.activity.LoginActivity;
import com.cap0394.sahabattani.activity.RegisterActivity;

public class HomeFragment extends Fragment {

    CardView menuFindTengkulak, menuCheckPrice, menuPriceForecast, menuMarketplace, menuAskExpert, menuInfoPertanian;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        menuFindTengkulak = root.findViewById(R.id.menu_find_tengkulak);
        menuFindTengkulak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_find_tengkulak);
            }
        });

        menuCheckPrice = root.findViewById(R.id.menu_check_price);
        menuCheckPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_price);
            }
        });

        menuPriceForecast = root.findViewById(R.id.menu_forecast_price);
        menuPriceForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_forecast);
            }
        });

        menuMarketplace = root.findViewById(R.id.menu_marketplace);
        menuMarketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_marketplace);
            }
        });

        menuInfoPertanian = root.findViewById(R.id.menu_info_pertanian);
        menuInfoPertanian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_info);
            }
        });

        menuAskExpert = root.findViewById(R.id.menu_ask_expert);
        menuAskExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KonsultasiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return root;
    }
}