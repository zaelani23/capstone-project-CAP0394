package com.cap0394.sahabattani.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cap0394.sahabattani.R;
import com.cap0394.sahabattani.api.ApiClient;
import com.cap0394.sahabattani.modul.EditProfileRequest;
import com.cap0394.sahabattani.modul.EditProfileResponse;
import com.cap0394.sahabattani.modul.GetProfileRequest;
import com.cap0394.sahabattani.modul.GetProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "sahabattani";
    // key for storing user_id.
    public static final String USER_ID_KEY = "user_id_key";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    // key for storing nama.
    public static final String NAMA_KEY = "nama_key";

    TextView editNama, editEmail, editDesa, editKecamatan, editKabupaten, editProvinsi;
    Button btnUpdate;

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profil");
        }

        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        userId = sharedpreferences.getString(USER_ID_KEY, "");

        // Call function getProfile()
        getProfile();

        editNama = findViewById(R.id.edit_nama);
        editEmail = findViewById(R.id.edit_email);
        editDesa = findViewById(R.id.edit_desa);
        editKecamatan = findViewById(R.id.edit_kecamatan);
        editKabupaten = findViewById(R.id.edit_kabupaten);
        editProvinsi = findViewById(R.id.edit_provinsi);
        btnUpdate = findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editNama.getText().toString()) || TextUtils.isEmpty(editEmail.getText().toString())){
                    Toast.makeText(ProfileActivity.this,"Nama dan email tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else{
                    editUserProfile();
                }
            }
        });
    }

    public void getProfile(){
        GetProfileRequest getProfileRequest = new GetProfileRequest();
        getProfileRequest.setUser_id(userId);

        Call<GetProfileResponse> getProfileResponseCall = ApiClient.getUserService().userProfile(getProfileRequest);
        getProfileResponseCall.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response.isSuccessful()){
                    GetProfileResponse getProfileResponse = response.body();
                    editNama.setText(getProfileResponse.getNama());
                    editEmail.setText(getProfileResponse.getEmail());
                    editDesa.setText(getProfileResponse.getDesa());
                    editKecamatan.setText(getProfileResponse.getKecamatan());
                    editKabupaten.setText(getProfileResponse.getKabupaten());
                    editProvinsi.setText(getProfileResponse.getProvinsi());
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void editUserProfile(){
        EditProfileRequest editProfileRequest = new EditProfileRequest();
        editProfileRequest.setUser_id(userId);
        editProfileRequest.setNama(editNama.getText().toString());
        editProfileRequest.setEmail(editEmail.getText().toString());
        editProfileRequest.setDesa(editDesa.getText().toString());
        editProfileRequest.setKecamatan(editKecamatan.getText().toString());
        editProfileRequest.setKabupaten(editKabupaten.getText().toString());
        editProfileRequest.setProvinsi(editProvinsi.getText().toString());

        Call<EditProfileResponse> editProfileResponseCall = ApiClient.getUserService().editUserProfile(editProfileRequest);
        editProfileResponseCall.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if (response.isSuccessful()){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(EMAIL_KEY, editEmail.getText().toString());
                    editor.putString(NAMA_KEY, editNama.getText().toString());
                    editor.apply();

                    Toast.makeText(ProfileActivity.this,"Profil berhasil diperbarui", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ProfileActivity.this,"Terjadi kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}