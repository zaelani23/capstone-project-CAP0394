package com.cap0394.sahabattani.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cap0394.sahabattani.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profil");
        }
    }
}