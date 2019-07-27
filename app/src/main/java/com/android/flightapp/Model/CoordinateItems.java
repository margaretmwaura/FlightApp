package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class CoordinateItems
{

    @SerializedName("Longitude")
    float longitude = 0.0000F;
    @SerializedName("Latitude")
    float latitude = 0.0000F;

    public CoordinateItems(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
