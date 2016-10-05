package com.lilla.homestruction.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lilla on 05/10/16.
 */

public class Humidity {
    @Expose
    @SerializedName("time_recorded")
    private String timeRecorded;
    @Expose
    private double value;

    public Humidity() {
        this(null, 0);
    }

    public Humidity(String timeRecorded, double value) {
        this.timeRecorded = timeRecorded;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTimeRecorded() {
        return timeRecorded;
    }

    public void setTimeRecorded(String timeRecorded) {
        this.timeRecorded = timeRecorded;
    }
}
