package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Flights
{
    @SerializedName("Flight")
    List<Flight> flights;

    public Flights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights()
    {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
