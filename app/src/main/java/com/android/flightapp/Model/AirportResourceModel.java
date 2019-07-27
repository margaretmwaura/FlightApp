package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class AirportResourceModel
{
    @SerializedName("AirportResource")
    public AirportResource airportResource;

    public AirportResource getAirportResource() {
        return airportResource;
    }

    public void setAirportResource(AirportResource airportResource) {
        this.airportResource = airportResource;
    }

    public AirportResourceModel(AirportResource airportResource) {
        this.airportResource = airportResource;
    }
}
