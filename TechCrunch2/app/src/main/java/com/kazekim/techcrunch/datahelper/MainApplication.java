package com.kazekim.techcrunch.datahelper;

import android.app.Application;
import android.util.DisplayMetrics;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class MainApplication extends Application {

    public static DisplayMetrics displayMetrics;

    public static boolean isDemoConfig = false;
    public static boolean isByPassSplash = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Contextor.getInstance().init(getApplicationContext());

        displayMetrics = getResources().getDisplayMetrics();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
