package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedules implements Parcelable
{
    @SerializedName("Schedule")
    List<Flights> schedules;

    public Schedules(List<Flights> schedules) {
        this.schedules = schedules;
    }

    protected Schedules(Parcel in) {
        schedules = in.createTypedArrayList(Flights.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(schedules);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Schedules> CREATOR = new Creator<Schedules>() {
        @Override
        public Schedules createFromParcel(Parcel in) {
            return new Schedules(in);
        }

        @Override
        public Schedules[] newArray(int size) {
            return new Schedules[size];
        }
    };

    public List<Flights> getFlights() {
        return schedules;
    }

    public void setFlights(List<Flights> schedules) {
        this.schedules = schedules;
    }
}
