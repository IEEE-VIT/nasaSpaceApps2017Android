package com.ieeevit.spaceappsvellore.rest;

import com.ieeevit.spaceappsvellore.models.LoginResponse;
import com.ieeevit.spaceappsvellore.models.MentorsResponse;
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

    @GET("mentors")
    Call<MentorsResponse> getMentors();

    @POST("admin/attendance")
    Call<LoginResponse> postAttendance(@Body LoginResponse loginResponse);

    @POST("admin/lunch1")
    Call<LoginResponse> postLunch1(@Body LoginResponse loginResponse);

    @POST("admin/lunch2")
    Call<LoginResponse> postLunch2(@Body LoginResponse loginResponse);

    @POST("admin/dinner")
    Call<LoginResponse> postDinner(@Body LoginResponse loginResponse);
}