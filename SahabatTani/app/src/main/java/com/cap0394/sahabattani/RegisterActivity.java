package com.cap0394.sahabattani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cap0394.sahabattani.API.ApiClient;
import com.cap0394.sahabattani.Modul.RegisterRequest;
import com.cap0394.sahabattani.Modul.RegisterResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    TextInputEditText nama, email, password, confirm_password;
    Button btnRegister;
    Spinner role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Buat Akun");
        }

        nama = findViewById(R.id.regNama);
        email = findViewById(R.id.regEmail);
        password = findViewById(R.id.regPassword);
        confirm_password = findViewById(R.id.regRePassword);
        role = findViewById(R.id.spinRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roleArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nama.getText().toString()) ||TextUtils.isEmpty(email.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(confirm_password.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Complete all fields", Toast.LENGTH_LONG).show();
                }else{
                    // Proses register
                    register();
                }
            }
        });

    }
    public void register(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setNama(nama.getText().toString());
        registerRequest.setEmail(email.getText().toString());
        registerRequest.setPassword(password.getText().toString());
        registerRequest.setConfirm_password(confirm_password.getText().toString());
        registerRequest.setRole(role.getSelectedItem().toString());

        Call<RegisterResponse> registerResponseCall = ApiClient.getUserService().userRegister(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Register Successful, Please Login", Toast.LENGTH_LONG).show();
                    RegisterResponse registerResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    },700);
                }else{
                    Toast.makeText(RegisterActivity.this,"Register Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
