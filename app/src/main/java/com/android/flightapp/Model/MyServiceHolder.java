package com.android.flightapp.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.android.flightapp.Presenter.api_service;

import androidx.annotation.Nullable;

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
