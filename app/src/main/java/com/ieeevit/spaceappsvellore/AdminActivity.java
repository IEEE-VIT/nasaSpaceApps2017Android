package com.ieeevit.spaceappsvellore;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ieeevit.spaceappsvellore.models.LoginResponse;
import com.ieeevit.spaceappsvellore.rest.ApiClient;
import com.ieeevit.spaceappsvellore.rest.ApiInterface;
import com.ieeevit.spaceappsvellore.utility.Consts;
import com.ieeevit.spaceappsvellore.utility.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.bt_qr_attendance)
    Button attendance;

    @BindView(R.id.bt_qr_lunch1)
    Button lunch1;

    @BindView(R.id.bt_qr_lunch2)
    Button lunch2;

    @BindView(R.id.bt_qr_dinner)
    Button dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        }

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, QRScanActivity.class);
                startActivityForResult(intent, Consts.QR_ATTENDANCE);
            }
        });
        lunch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, QRScanActivity.class);
                startActivityForResult(intent, Consts.QR_LUNCH1);
            }
        });
        lunch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, QRScanActivity.class);
                startActivityForResult(intent, Consts.QR_LUNCH2);
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, QRScanActivity.class);
                startActivityForResult(intent, Consts.QR_DINNER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final ProgressDialog dialog = new ProgressDialog(AdminActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final ApiInterface apiInterface = ApiClient.getClient(AdminActivity.this).create(ApiInterface.class);

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                String requiredValue = data.getStringExtra(Consts.QRCODE);

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setCode(requiredValue);

                Call<LoginResponse> loginResponseCall;

                switch (requestCode) {
                    case Consts.QR_ATTENDANCE:
                        loginResponseCall = apiInterface.postAttendance(loginResponse);
                        break;
                    case Consts.QR_LUNCH1:
                        loginResponseCall = apiInterface.postLunch1(loginResponse);
                        break;
                    case Consts.QR_LUNCH2:
                        loginResponseCall = apiInterface.postLunch2(loginResponse);
                        break;
                    case Consts.QR_DINNER:
                        loginResponseCall = apiInterface.postDinner(loginResponse);
                        break;
                    default:
                        loginResponseCall = apiInterface.postAttendance(loginResponse);
                        break;
                }

                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        dialog.hide();
                        Toast.makeText(AdminActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        dialog.hide();
                        DialogUtil.createDialog("Network Problem! Please try again!", AdminActivity.this, null);
                    }
                });
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
