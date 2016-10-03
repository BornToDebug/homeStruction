package com.lilla.homestruction.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by lilla on 03/10/16.
 */

public class Lamp2Response {
    @Expose
    private int count;
    @Expose
    private String next;
    @Expose
    private String previous;
    @Expose
    private List<Lamp2> results;

    public Lamp2Response() {
        this(0, null, null, null);
    }

    public Lamp2Response(int count, String next, String previous, List<Lamp2> results) {
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

    public List<Lamp2> getResults() {
        return results;
    }

    public void setResults(List<Lamp2> results) {
        this.results = results;
    }
}
