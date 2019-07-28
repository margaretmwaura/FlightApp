package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirportResource implements Parcelable
{
    @SerializedName("Airports")
    public Airports airports;

    public AirportResource(Airports airports) {
        this.airports = airports;
    }


    protected AirportResource(Parcel in)
    {
        airports = in.readParcelable(Airport.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(airports,flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportResource> CREATOR = new Creator<AirportResource>() {
        @Override
        public AirportResource createFromParcel(Parcel in) {
            return new AirportResource(in);
        }

        @Override
        public AirportResource[] newArray(int size) {
            return new AirportResource[size];
        }
    };

    public Airports getAirports() {
        return airports;
    }

    public void setAirports(Airports airports) {
        this.airports = airports;
    }
}
