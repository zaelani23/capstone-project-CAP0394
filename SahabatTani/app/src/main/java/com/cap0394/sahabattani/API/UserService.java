package com.cap0394.sahabattani.API;

import com.cap0394.sahabattani.Modul.LoginRequest;
import com.cap0394.sahabattani.Modul.LoginResponse;
import com.cap0394.sahabattani.Modul.RegisterRequest;
import com.cap0394.sahabattani.Modul.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/auth/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("api/auth/register")
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);

}