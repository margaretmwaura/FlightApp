package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedules
{
    @SerializedName("Schedule")
    List<Flights> schedules;

    public Schedules(List<Flights> schedules) {
        this.schedules = schedules;
    }

    public List<Flights> getFlights() {
        return schedules;
    }

    public void setFlights(List<Flights> schedules) {
        this.schedules = schedules;
    }
}
