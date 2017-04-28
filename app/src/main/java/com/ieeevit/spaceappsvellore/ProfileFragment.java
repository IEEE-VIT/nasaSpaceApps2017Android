package com.ieeevit.spaceappsvellore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ieeevit.spaceappsvellore.models.SignUp;
import com.ieeevit.spaceappsvellore.utility.Consts;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    SignUp signUp;
    @BindView(R.id.tv_profile_name)
    TextView name;

    @BindView(R.id.tv_profile_location)
    TextView location;

    @BindView(R.id.iv_profile_qr)
    ImageView qr;

    @BindView(R.id.prof_wifi_username)
    TextView wifi_username;

    @BindView(R.id.prof_wifi_pass)
    TextView wifi_password;

    @BindView(R.id.cv_wifi)
    CardView cv_wifi;

    @BindView(R.id.cv_lunch1)
    CardView cv_lunch1;

    @BindView(R.id.cv_lunch2)
    CardView cv_lunch2;

    @BindView(R.id.cv_dinner)
    CardView cv_dinner;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(SignUp signUp) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(Consts.PROFILE, signUp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            signUp = (SignUp) getArguments().getSerializable(Consts.PROFILE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);

        Glide.with(this).load("http://api.qrserver.com/v1/create-qr-code/?data=" + signUp.getActivationToken() + "&size=100x100")
                .into(qr);

        name.setText(signUp.getName());
        location.setText(signUp.getCollege());

        if(signUp.getWifiUsername()==null){
            cv_wifi.setVisibility(View.GONE);
        }
        else {
            wifi_username.setText(signUp.getWifiUsername());
            wifi_password.setText(signUp.getWifiPassword());
        }
        if(signUp.getLunch1()){
            cv_lunch1.setVisibility(View.GONE);
        }

        if(signUp.getLunch2()){
            cv_lunch2.setVisibility(View.GONE);
        }

        if(signUp.getDinner()){
            cv_dinner.setVisibility(View.GONE);
        }

        return v;
    }

}
