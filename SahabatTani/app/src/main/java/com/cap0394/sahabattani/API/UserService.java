package com.cap0394.sahabattani.api;

import com.cap0394.sahabattani.modul.EditProfileRequest;
import com.cap0394.sahabattani.modul.EditProfileResponse;
import com.cap0394.sahabattani.modul.GetProfileRequest;
import com.cap0394.sahabattani.modul.GetProfileResponse;
import com.cap0394.sahabattani.modul.LoginRequest;
import com.cap0394.sahabattani.modul.LoginResponse;
import com.cap0394.sahabattani.modul.RegisterRequest;
import com.cap0394.sahabattani.modul.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("api/auth/register")
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);

    @POST("api/profil")
    Call<GetProfileResponse> userProfile(@Body GetProfileRequest getProfileRequest);

    @POST("api/edit-profil")
    Call<EditProfileResponse> editUserProfile(@Body EditProfileRequest editProfileRequest);
}