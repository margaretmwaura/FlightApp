package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirportResourceModel implements Parcelable
{
    @SerializedName("AirportResource")
    public AirportResource airportResource;

    protected AirportResourceModel(Parcel in)
    {
        airportResource = in.readParcelable(AirportResource.class.getClassLoader());
    }

    public static final Creator<AirportResourceModel> CREATOR = new Creator<AirportResourceModel>() {
        @Override
        public AirportResourceModel createFromParcel(Parcel in) {
            return new AirportResourceModel(in);
        }

        @Override
        public AirportResourceModel[] newArray(int size) {
            return new AirportResourceModel[size];
        }
    };

    public AirportResource getAirportResource() {
        return airportResource;
    }

    public void setAirportResource(AirportResource airportResource) {
        this.airportResource = airportResource;
    }

    public AirportResourceModel(AirportResource airportResource) {
        this.airportResource = airportResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(airportResource, flags);
    }
}
