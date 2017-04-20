package com.ieeevit.spaceappsvellore;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Karishnu Poddar on 20/04/2017.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}