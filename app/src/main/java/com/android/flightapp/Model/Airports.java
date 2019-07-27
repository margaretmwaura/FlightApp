package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Airports
{
    @SerializedName("Airport")
    List<Airport> airPorts;

    public Airports(List<Airport> airPorts) {
        this.airPorts = airPorts;
    }

    public List<Airport> getAirPortLists() {
        return airPorts;
    }

    public void setAirPortsList(List<Airport> airPorts) {
        this.airPorts = airPorts;
    }
}
