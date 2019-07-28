package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class Flight
{
    @SerializedName("MarketingCarrier")
    MarketCourier marketCourier;

    public Flight(MarketCourier marketCourier) {
        this.marketCourier = marketCourier;
    }

    public MarketCourier getMarketCourier() {
        return marketCourier;
    }

    public void setMarketCourier(MarketCourier marketCourier) {
        this.marketCourier = marketCourier;
    }
}
