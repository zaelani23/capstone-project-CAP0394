package com.cap0394.sahabattani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardTengkuak extends AppCompatActivity {

    TextView userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_tengkuak);

        userId = findViewById(R.id.userId);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            String passedId = intent.getStringExtra("data");
            userId.setText("Welcome Tengkulak "+passedId);
        }
    }
}