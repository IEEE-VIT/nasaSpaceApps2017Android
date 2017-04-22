package com.ieeevit.spaceappsvellore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addSlide(AppIntroFragment.newInstance(title, description, image, backgroundColor));
    }
}
