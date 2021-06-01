package com.cap0394.sahabattani.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.api.ApiClient;
import com.cap0394.sahabattani.modul.LoginRequest;
import com.cap0394.sahabattani.modul.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txtDaftar;
    Button btnLogin;
    EditText inputEmail, inputPassword;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "sahabattani";

    // key for storing user_id.
    public static final String USER_ID_KEY = "user_id_key";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing nama.
    public static final String NAMA_KEY = "nama_key";

    // key for storing role.
    public static final String ROLE_KEY = "role_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String userId, email, nama, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        txtDaftar = findViewById(R.id.gotoRegister);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        userId = sharedpreferences.getString(USER_ID_KEY, "");
        email = sharedpreferences.getString(EMAIL_KEY, "");
        nama = sharedpreferences.getString(NAMA_KEY, "");
        role = sharedpreferences.getString(ROLE_KEY, "");

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputEmail.getText().toString()) || TextUtils.isEmpty(inputPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Email / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    // Proses login
                    login();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!userId.equals("")) {
            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(i);
        }
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(inputEmail.getText().toString());
        loginRequest.setPassword(inputPassword.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(USER_ID_KEY, loginResponse.getUser_id());
                    editor.putString(EMAIL_KEY, loginResponse.getEmail());
                    editor.putString(NAMA_KEY, loginResponse.getNama());
                    editor.putString(ROLE_KEY, loginResponse.getRole());
                    editor.apply();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loginResponse.getRole().equals("petani")){
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    },700);
                }else{
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}