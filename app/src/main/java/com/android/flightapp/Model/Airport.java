package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Airport implements Parcelable
{
    @SerializedName("AirportCode")
    String airportCode;
    @SerializedName("Position")
    Coordinate position;
    @SerializedName("CityCode")
    String cityCode;
    @SerializedName("CountryCode")
    String countryCode;


    protected Airport(Parcel in) {
        airportCode = in.readString();
        position = in.readParcelable(Coordinate.class.getClassLoader());
        cityCode = in.readString();
        countryCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airportCode);
        dest.writeParcelable(position, flags);
        dest.writeString(cityCode);
        dest.writeString(countryCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Airport> CREATOR = new Creator<Airport>() {
        @Override
        public Airport createFromParcel(Parcel in) {
            return new Airport(in);
        }

        @Override
        public Airport[] newArray(int size) {
            return new Airport[size];
        }
    };

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
