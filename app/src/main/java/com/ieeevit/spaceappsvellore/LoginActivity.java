package com.ieeevit.spaceappsvellore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ieeevit.spaceappsvellore.models.LoginResponse;
import com.ieeevit.spaceappsvellore.models.SignUp;
import com.ieeevit.spaceappsvellore.rest.ApiClient;
import com.ieeevit.spaceappsvellore.rest.ApiInterface;
import com.ieeevit.spaceappsvellore.utility.Consts;
import com.ieeevit.spaceappsvellore.utility.DialogUtil;
import com.ieeevit.spaceappsvellore.utility.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_email) EditText email;
    @BindView(R.id.et_login_pass) EditText pass;
    @BindView(R.id.bt_login_submit) Button submit;
    @BindView(R.id.tv_login_signup) TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!Preferences.getPrefs(Consts.TOKEN_SP_KEY, this).equals(Consts.NOT_FOUND)){
            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }

        ButterKnife.bind(this);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Loading. Please wait...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                final ApiInterface apiInterface = ApiClient.getClient(LoginActivity.this).create(ApiInterface.class);

                SignUp signUp = new SignUp();
                signUp.setEmail(email.getText().toString());
                signUp.setPassword(pass.getText().toString());
                Call<LoginResponse> loginResponseCall = apiInterface.login(signUp);

                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        dialog.hide();

                        if(response.body().getCode().equals(Consts.SUCCESS)){
                            Preferences.setPrefs(Consts.TOKEN_SP_KEY, response.body().getToken(), LoginActivity.this);
                            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            DialogUtil.createDialog(response.body().getMessage(), LoginActivity.this ,null);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        dialog.hide();
                    }
                });
            }
        });

    }
}
