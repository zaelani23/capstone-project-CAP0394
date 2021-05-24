package com.cap0394.sahabattani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cap0394.sahabattani.API.ApiClient;
import com.cap0394.sahabattani.Modul.LoginRequest;
import com.cap0394.sahabattani.Modul.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText email, password;
    Button btnLogin, btnBuatAKun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.edEmail);
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBuatAKun = findViewById(R.id.btnBuatAkun);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this,"Email / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    // Proses login
                    login();
                }
            }
        });

        btnBuatAKun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call <LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loginResponse.getRole().equals("petani")){
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("data",loginResponse.getUser_id());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(MainActivity.this, DashboardTengkuak.class);
                                intent.putExtra("data",loginResponse.getUser_id());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    },700);
                }else{
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}