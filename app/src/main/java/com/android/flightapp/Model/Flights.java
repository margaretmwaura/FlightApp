package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Flights implements Parcelable
{
    @SerializedName("Flight")
    List<Flight> flights;

    public Flights(List<Flight> flights) {
        this.flights = flights;
    }

    protected Flights(Parcel in) {
        flights = in.createTypedArrayList(Flight.CREATOR);
    }

    public static final Creator<Flights> CREATOR = new Creator<Flights>() {
        @Override
        public Flights createFromParcel(Parcel in) {
            return new Flights(in);
        }

        @Override
        public Flights[] newArray(int size) {
            return new Flights[size];
        }
    };

    public List<Flight> getFlights()
    {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(flights);
    }
}
