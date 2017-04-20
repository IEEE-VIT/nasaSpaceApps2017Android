package com.ieeevit.spaceappsvellore;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountdownFragment extends Fragment {

    TextView days, hours, mins;


    public CountdownFragment() {
        // Required empty public constructor
    }

    public static CountdownFragment newInstance() {
        CountdownFragment fragment = new CountdownFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_countdown, container, false);

        days = (TextView) v.findViewById(R.id.tv_days);
        hours = (TextView) v.findViewById(R.id.tv_hours);
        mins = (TextView) v.findViewById(R.id.tv_mins);

        long totaltime = (new DateTime("2017-04-29T02:30Z")).toDate().getTime();
        long starttime = (Calendar.getInstance()).getTime().getTime();

        new CountDownTimer( totaltime - starttime , 1000) {
            public void onTick(long millisUntilFinished) {
                long day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                long hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(day);
                long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - (TimeUnit.DAYS.toMinutes(day) + TimeUnit.HOURS.toMinutes(hour));
                days.setText(String.valueOf(day));
                hours.setText(String.valueOf(hour));
                mins.setText(String.valueOf(min));
            }

            public void onFinish() {
            }

        }.start();

        return v;
    }

}
