package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Coordinate implements Parcelable
{
    @SerializedName("Coordinate")
    CoordinateItems coordinateItems;

    public Coordinate(CoordinateItems coordinateItems) {
        this.coordinateItems = coordinateItems;
    }

    protected Coordinate(Parcel in)
    {
        coordinateItems = in.readParcelable(CoordinateItems.class.getClassLoader());
    }

    public static final Creator<Coordinate> CREATOR = new Creator<Coordinate>() {
        @Override
        public Coordinate createFromParcel(Parcel in) {
            return new Coordinate(in);
        }

        @Override
        public Coordinate[] newArray(int size) {
            return new Coordinate[size];
        }
    };

    public CoordinateItems getCoordinateItems() {
        return coordinateItems;
    }

    public void setCoordinateItems(CoordinateItems coordinateItems) {
        this.coordinateItems = coordinateItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(coordinateItems,flags);
    }
}
