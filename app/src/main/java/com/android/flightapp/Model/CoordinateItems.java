package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CoordinateItems implements Parcelable
{
    @SerializedName("Latitude")
    float latitude = 0.0000F;
    @SerializedName("Longitude")
    float longitude = 0.0000F;


    public CoordinateItems(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }


    protected CoordinateItems(Parcel in) {
        latitude = in.readFloat();
        longitude = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CoordinateItems> CREATOR = new Creator<CoordinateItems>() {
        @Override
        public CoordinateItems createFromParcel(Parcel in) {
            return new CoordinateItems(in);
        }

        @Override
        public CoordinateItems[] newArray(int size) {
            return new CoordinateItems[size];
        }
    };

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
