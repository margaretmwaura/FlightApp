package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class Coordinate
{
    @SerializedName("Coordinate")
    CoordinateItems coordinateItems;

    public Coordinate(CoordinateItems coordinateItems) {
        this.coordinateItems = coordinateItems;
    }

    public CoordinateItems getCoordinateItems() {
        return coordinateItems;
    }

    public void setCoordinateItems(CoordinateItems coordinateItems) {
        this.coordinateItems = coordinateItems;
    }
}
