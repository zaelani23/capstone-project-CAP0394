package com.cap0394.sahabattani.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cap0394.sahabattani.R;

public class KonsultasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Konsultasi");
        }
    }
}