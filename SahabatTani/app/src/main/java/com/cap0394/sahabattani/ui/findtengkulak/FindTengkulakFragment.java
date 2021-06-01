package com.cap0394.sahabattani.ui.findtengkulak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.Tengkulak;
import com.cap0394.sahabattani.TengkulakData;
import com.cap0394.sahabattani.adapter.CardViewTengkulakAdapter;

import java.util.ArrayList;

public class FindTengkulakFragment extends Fragment {

    private FindTengkulakViewModel findTengkulakViewModel;
    private RecyclerView rvTengkulak;
    private ArrayList<Tengkulak> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        findTengkulakViewModel = new ViewModelProvider(this).get(FindTengkulakViewModel.class);
        View root = inflater.inflate(R.layout.fragment_find_tengkulak, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        findTengkulakViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        list.addAll(TengkulakData.getListData());

        rvTengkulak = view.findViewById(R.id.rv_tengkulak);
        rvTengkulak.setHasFixedSize(true);
        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvTengkulak.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewTengkulakAdapter cardViewTengkulakAdapter = new CardViewTengkulakAdapter(list);
        rvTengkulak.setAdapter(cardViewTengkulakAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_find_tengkulak, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}