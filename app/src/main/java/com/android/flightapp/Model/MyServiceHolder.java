package com.android.flightapp.Model;

import android.support.annotation.Nullable;

import com.android.flightapp.Presenter.api_service;

public class MyServiceHolder
{
    api_service myService = null;

    @Nullable
    public api_service get() {
        return myService;
    }

    public void set(api_service myService) {
        this.myService = myService;
    }
}
