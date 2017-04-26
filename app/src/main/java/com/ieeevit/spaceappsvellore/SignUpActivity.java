package com.ieeevit.spaceappsvellore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_submit) Button button;
    @BindView(R.id.signup_alien_technical) CircleImageView technical;
    @BindView(R.id.signup_alien_design) CircleImageView design;
    @BindView(R.id.signup_alien_mngt) CircleImageView mgmt;

    @BindView(R.id.et_name) TextView name;
    @BindView(R.id.et_email) TextView email;
    @BindView(R.id.et_org) TextView org;
    @BindView(R.id.et_pass) TextView pass;
    @BindView(R.id.tv_signup_login) TextView login;

    String skill;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(skill == null || org.getText().toString().equals("") || name.getText().toString().equals("") || email.getText().toString().equals("") || pass.getText().toString().equals("")){
                    DialogUtil.createDialog("Please fill in all fields correctly!", SignUpActivity.this, null);
                }
                else {

                    final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Loading. Please wait...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    final ApiInterface apiInterface = ApiClient.getClient(SignUpActivity.this).create(ApiInterface.class);
                    SignUp signUp = new SignUp();
                    signUp.setCollege(org.getText().toString());
                    signUp.setName(name.getText().toString());
                    signUp.setSkill(skill);
                    signUp.setEmail(email.getText().toString());
                    signUp.setPassword(pass.getText().toString());
                    Call<LoginResponse> signUpCall = apiInterface.signUp(signUp);

                    signUpCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            dialog.hide();
                            if (response.body().getCode().equals(Consts.SUCCESS)) {
                                Preferences.setPrefs(Consts.TOKEN_SP_KEY, response.body().getToken(), SignUpActivity.this);
                                Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                DialogUtil.createDialog(response.body().getMessage(), SignUpActivity.this, new DialogUtil.OnPositiveButtonClick() {
                                    @Override
                                    public void onClick() {
                                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            dialog.hide();
                        }
                    });
                }
            }
        });

        technical.setOnClickListener(this);
        design.setOnClickListener(this);
        mgmt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.signup_alien_technical:
                technical.setBorderColor(getResources().getColor(R.color.red));
                design.setBorderColor(getResources().getColor(R.color.white));
                mgmt.setBorderColor(getResources().getColor(R.color.white));

                skill = Consts.TECHNICAL;
                break;
            case R.id.signup_alien_design:
                technical.setBorderColor(getResources().getColor(R.color.white));
                design.setBorderColor(getResources().getColor(R.color.red));
                mgmt.setBorderColor(getResources().getColor(R.color.white));

                skill = Consts.DESIGN;
                break;
            case R.id.signup_alien_mngt:
                technical.setBorderColor(getResources().getColor(R.color.white));
                design.setBorderColor(getResources().getColor(R.color.white));
                mgmt.setBorderColor(getResources().getColor(R.color.red));

                skill = Consts.MANAGEMENT;
                break;
        }
    }
}
