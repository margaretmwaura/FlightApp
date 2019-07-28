package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MarketCourier implements Parcelable
{
    @SerializedName("AirlineID")
    String airplaneId;
    @SerializedName("FlightNumber")
    int flightNumber;

    public MarketCourier(String airplaneId, int flightNumber) {
        this.airplaneId = airplaneId;
        this.flightNumber = flightNumber;
    }

    protected MarketCourier(Parcel in) {
        airplaneId = in.readString();
        flightNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airplaneId);
        dest.writeInt(flightNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MarketCourier> CREATOR = new Creator<MarketCourier>() {
        @Override
        public MarketCourier createFromParcel(Parcel in) {
            return new MarketCourier(in);
        }

        @Override
        public MarketCourier[] newArray(int size) {
            return new MarketCourier[size];
        }
    };

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
