package com.lilla.homestruction.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lilla on 26/11/16.
 */

public class LatestData {
    @Expose
    @SerializedName("values")
    private List<String> values;

    public LatestData() {
        this(null);
    }

    public LatestData(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return this.values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
