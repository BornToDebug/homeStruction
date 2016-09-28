package com.lilla.homestruction.bean;

import android.view.Window;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by lilla on 28/09/16.
 */

public class WindowsResponse {
    @Expose
    private int count;
    @Expose
    private String next;
    @Expose
    private String previous;
    @Expose
    private List<Windows> results;

    public WindowsResponse() {
        this(0, null, null, null);
    }

    public WindowsResponse(int count, String next, String previous, List<Windows> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Windows> getResults() {
        return results;
    }

    public void setResults(List<Windows> results) {
        this.results = results;
    }
}
