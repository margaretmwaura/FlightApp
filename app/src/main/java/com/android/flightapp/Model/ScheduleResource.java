package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class ScheduleResource
{
    @SerializedName("ScheduleResource")
    Schedules schedules;

    public ScheduleResource(Schedules schedules) {
        this.schedules = schedules;
    }

    public Schedules getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedules schedules) {
        this.schedules = schedules;
    }
}
