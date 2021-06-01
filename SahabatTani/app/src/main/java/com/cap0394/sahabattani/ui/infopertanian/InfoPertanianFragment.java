package com.cap0394.sahabattani.ui.infopertanian;

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

import com.cap0394.sahabattani.InfoPertanian;
import com.cap0394.sahabattani.InfoPertanianData;
import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.adapter.CardViewInfoPertanianAdapter;

import java.util.ArrayList;

public class InfoPertanianFragment extends Fragment {

    private InfoPertanianViewModel infoPertanianViewModelViewModel;
    private RecyclerView rvInfoPertanian;
    private ArrayList<InfoPertanian> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        infoPertanianViewModelViewModel = new ViewModelProvider(this).get(InfoPertanianViewModel.class);
        View root = inflater.inflate(R.layout.fragment_info_pertanian, container, false);
        /*final TextView textView = root.findViewById(R.id.text_info_pertanian);
        infoPertanianViewModelViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        list.addAll(InfoPertanianData.getListData());

        rvInfoPertanian = view.findViewById(R.id.rv_info_pertanian);
        rvInfoPertanian.setHasFixedSize(true);
        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvInfoPertanian.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewInfoPertanianAdapter cardViewInfoPertanianAdapter = new CardViewInfoPertanianAdapter(list);
        rvInfoPertanian.setAdapter(cardViewInfoPertanianAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_appbar_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}