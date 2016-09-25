package com.example.lilla.homestruction.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lilla on 25/09/16.
 */

public class Temperature {


    @Expose
    @SerializedName("time_recorded")
    private String timeRecorded;
    @Expose
    private double value;

    public Temperature() {
        this(null, 0);
    }

    public Temperature(String timeRecorded, double value) {
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
