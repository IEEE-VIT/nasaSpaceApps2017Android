package com.ieeevit.spaceappsvellore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ieeevit.spaceappsvellore.models.MentorsResponse;
import com.ieeevit.spaceappsvellore.models.SignUp;
import com.ieeevit.spaceappsvellore.rest.ApiClient;
import com.ieeevit.spaceappsvellore.rest.ApiInterface;
import com.ieeevit.spaceappsvellore.utility.Consts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ApiInterface apiInterface = ApiClient.getClient(SplashActivity.this).create(ApiInterface.class);
        Call<SignUp> profileCall = apiInterface.getProfile();
        final Call<MentorsResponse> mentorsResponseCall = apiInterface.getMentors();

        profileCall.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, final Response<SignUp> profileresponse) {

                mentorsResponseCall.enqueue(new Callback<MentorsResponse>() {
                    @Override
                    public void onResponse(Call<MentorsResponse> call, Response<MentorsResponse> response) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra(Consts.PROFILE, profileresponse.body());
                        intent.putExtra(Consts.MENTOR, response.body());
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<MentorsResponse> call, Throwable t) {}
                });
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {

            }
        });
    }
}
