package com.ieeevit.spaceappsvellore.rest;

import com.ieeevit.spaceappsvellore.models.LoginResponse;
import com.ieeevit.spaceappsvellore.models.SignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("auth/login")
    Call<LoginResponse> login(@Body SignUp authRequest);

    @POST("auth/save")
    Call<LoginResponse> signUp(@Body SignUp authRequest);

    @GET("profile")
    Call<SignUp> getProfile();
}