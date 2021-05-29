package com.cap0394.sahabattani.api;

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

}