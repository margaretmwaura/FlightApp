package com.android.flightapp.Model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class Airport
{
    @SerializedName("AirportCode")
    String airportCode;
    @SerializedName("CityCode")
    String cityCode;
    @SerializedName("CountryCode")
    String countryCode;
    @SerializedName("Position")
    Coordinate position;



    protected Airport(Parcel in) {
        airportCode = in.readString();
        cityCode = in.readString();
        countryCode = in.readString();
    }


    public String getAirportCode()
    {
        return this.airportCode;
    }
    public String getCityCode()
    {
        return this.cityCode;
    }
    public String getCountryCode()
    {
        return this.countryCode;
    }
    public void setAirportCode(String airportCode)
    {
        this.airportCode = airportCode;
    }
    public void setCityCode(String cityCode)
    {
        this.cityCode = cityCode;
    }
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }
    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
