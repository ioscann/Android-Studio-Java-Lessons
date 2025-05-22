package com.ioscan.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeTurkey {
    @SerializedName("utc_datetime")
    @Expose
    private String dateTime;

    @SerializedName("day_of_week")
    @Expose
    private int dayOfWeek;

    @SerializedName("week_number")
    @Expose
    private int weekNumber;

    @SerializedName("day_of_year")
    @Expose
    private int dayOfYear;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }
}
