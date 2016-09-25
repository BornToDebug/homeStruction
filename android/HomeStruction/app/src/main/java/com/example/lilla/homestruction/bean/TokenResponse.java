package com.example.lilla.homestruction.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by lilla on 25/09/16.
 */

public class TokenResponse {
    @Expose
    private String token;

    public TokenResponse() {
        this(null);
    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
