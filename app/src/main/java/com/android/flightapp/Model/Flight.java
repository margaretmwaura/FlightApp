package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Flight implements Parcelable
{
    @SerializedName("MarketingCarrier")
    MarketCourier marketCourier;

    public Flight(MarketCourier marketCourier) {
        this.marketCourier = marketCourier;
    }

    protected Flight(Parcel in)
    {
        marketCourier = in.readParcelable(MarketCourier.class.getClassLoader());
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    public MarketCourier getMarketCourier() {
        return marketCourier;
    }

    public void setMarketCourier(MarketCourier marketCourier) {
        this.marketCourier = marketCourier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(marketCourier,flags);
    }
}
