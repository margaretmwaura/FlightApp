package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class AirportResource
{
    @SerializedName("Airports")
    public Airports airports;

    public AirportResource(Airports airports) {
        this.airports = airports;
    }

    public Airports getAirports() {
        return airports;
    }

    public void setAirports(Airports airports) {
        this.airports = airports;
    }
}
