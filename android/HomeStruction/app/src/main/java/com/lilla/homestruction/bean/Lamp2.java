package com.lilla.homestruction.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lilla on 03/10/16.
 */

public class Lamp2 {
    @Expose
    @SerializedName("time_recorded")
    private String timeRecorded;
    @Expose
    private String value;

    public Lamp2() {
        this(null, null);
    }

    public Lamp2(String timeRecorded, String value) {
        this.timeRecorded = timeRecorded;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimeRecorded() {
        return timeRecorded;
    }

    public void setTimeRecorded(String timeRecorded) {
        this.timeRecorded = timeRecorded;
    }
}