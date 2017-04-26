package com.ieeevit.spaceappsvellore;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.ieeevit.spaceappsvellore.models.MentorsResponse;
import com.ieeevit.spaceappsvellore.models.SignUp;
import com.ieeevit.spaceappsvellore.utility.Consts;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_timeline:
                    return true;
            }
            return false;
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

       /* CountdownFragment todayFragment = CountdownFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, todayFragment).commit();*/

        ScheduleFragment scheduleFragment = new ScheduleFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, scheduleFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

                switch (item.getItemId()){

                    case R.id.navigation_profile:
                        ProfileFragment profileFragment = ProfileFragment.newInstance((SignUp) getIntent().getSerializableExtra(Consts.PROFILE));
                        ft.replace(R.id.container, profileFragment).commit();
                        return true;

                    case R.id.navigation_dashboard:
                        MentorFragment mentorFragment = MentorFragment.newInstance((MentorsResponse) getIntent().getSerializableExtra(Consts.MENTOR));
                        ft.replace(R.id.container, mentorFragment).commit();
                        return true;
                    case R.id.navigation_timeline:
                        ScheduleFragment scheduleFragment = new ScheduleFragment();
                        ft.replace(R.id.container, scheduleFragment).commit();
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}