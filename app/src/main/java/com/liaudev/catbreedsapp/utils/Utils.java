package com.liaudev.catbreedsapp.utils;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Budiliauw87 on 2022-10-22.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class Utils {
    public static boolean isNightMode (Context context){
        final int nightModeFlags =  context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
