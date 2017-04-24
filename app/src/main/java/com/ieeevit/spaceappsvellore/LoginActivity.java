package com.ieeevit.spaceappsvellore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ieeevit.spaceappsvellore.models.LoginResponse;
import com.ieeevit.spaceappsvellore.models.SignUp;
import com.ieeevit.spaceappsvellore.rest.ApiClient;
import com.ieeevit.spaceappsvellore.rest.ApiInterface;
import com.ieeevit.spaceappsvellore.utility.Consts;
import com.ieeevit.spaceappsvellore.utility.DialogUtil;
import com.ieeevit.spaceappsvellore.utility.Preferences;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_email)
    EditText email;
    @BindView(R.id.et_login_pass)
    EditText pass;
    @BindView(R.id.bt_login_submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ApiInterface apiInterface = ApiClient.getClient(LoginActivity.this).create(ApiInterface.class);

                SignUp signUp = new SignUp();
                signUp.setEmail(email.getText().toString());
                signUp.setPassword(pass.getText().toString());
                Call<LoginResponse> loginResponseCall = apiInterface.login(signUp);

                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.body().getCode().equals(Consts.SUCCESS)){
                            Preferences.setPrefs(Consts.TOKEN_SP_KEY, response.body().getToken(), LoginActivity.this);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            DialogUtil.createDialog(response.body().getMessage(), LoginActivity.this ,null);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
}
