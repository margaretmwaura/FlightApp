package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScheduleResource implements Parcelable
{
    @SerializedName("ScheduleResource")
    Schedules schedules;

    public ScheduleResource(Schedules schedules) {
        this.schedules = schedules;
    }

    protected ScheduleResource(Parcel in)
    {
        schedules = in.readParcelable(Schedules.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(schedules,flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduleResource> CREATOR = new Creator<ScheduleResource>() {
        @Override
        public ScheduleResource createFromParcel(Parcel in) {
            return new ScheduleResource(in);
        }

        @Override
        public ScheduleResource[] newArray(int size) {
            return new ScheduleResource[size];
        }
    };

    public Schedules getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedules schedules) {
        this.schedules = schedules;
    }
}
