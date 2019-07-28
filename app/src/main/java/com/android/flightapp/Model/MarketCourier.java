package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class MarketCourier
{
    @SerializedName("AirlineID")
    String airplaneId;
    @SerializedName("FlightNumber")
    int flightNumber;

    public MarketCourier(String airplaneId, int flightNumber) {
        this.airplaneId = airplaneId;
        this.flightNumber = flightNumber;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
}
