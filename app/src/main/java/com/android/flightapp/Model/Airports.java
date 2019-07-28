package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Airports implements Parcelable
{
    @SerializedName("Airport")
    List<Airport> airPorts;

    public Airports(List<Airport> airPorts) {
        this.airPorts = airPorts;
    }

    protected Airports(Parcel in) {
        airPorts = in.createTypedArrayList(Airport.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(airPorts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Airports> CREATOR = new Creator<Airports>() {
        @Override
        public Airports createFromParcel(Parcel in) {
            return new Airports(in);
        }

        @Override
        public Airports[] newArray(int size) {
            return new Airports[size];
        }
    };

    public List<Airport> getAirPortLists() {
        return airPorts;
    }

    public void setAirPortsList(List<Airport> airPorts) {
        this.airPorts = airPorts;
    }
}
