package com.ieeevit.spaceappsvellore.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Karishnu Poddar on 28/08/2016.
 */
public class Preferences {
    public static void setPrefs(String key, String value, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("IEEEPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(String key, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("IEEEPreferences", Context.MODE_PRIVATE);
        return sharedpreferences.getString(key, Consts.NOT_FOUND);
    }

    public static void clearPrefs(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("IEEEPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}
