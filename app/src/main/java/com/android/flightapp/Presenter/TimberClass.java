package com.android.flightapp.Presenter;

import android.app.Application;

import com.android.flightapp.BuildConfig;

import timber.log.Timber;

public class TimberClass extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
