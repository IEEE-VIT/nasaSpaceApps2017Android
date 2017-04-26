package com.ieeevit.spaceappsvellore;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieeevit.spaceappsvellore.models.MentorsResponse;
import com.ieeevit.spaceappsvellore.models.SignUp;
import com.ieeevit.spaceappsvellore.rest.ApiClient;
import com.ieeevit.spaceappsvellore.rest.ApiInterface;
import com.ieeevit.spaceappsvellore.utility.Consts;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_splash_fact)
    ImageView imageView;

    @BindView(R.id.tv_splash_fact)
    TextView textView;

    int img[] = {R.drawable.img_spaceship, R.drawable.img_planet, R.drawable.img_satellite};
    String strings[] = {"More than 40,000 Americans have taken out insurance against being abducted by aliens.",
            "Saturn is the sixth planet from the Sun and the most distant that can be seen with the naked eye.",
        "In October 1957, the Soviet Union's Sputnik 1 which was the world's first artificial satellite to be sent into space orbit."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        Random ran = new Random();
        int x = ran.nextInt(3);

        imageView.setImageResource(img[x]);
        textView.setText(strings[x]);

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
