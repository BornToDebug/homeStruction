package com.lilla.homestruction;

import com.lilla.homestruction.bean.TemperatureResponse;
import com.lilla.homestruction.bean.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lilla on 24/09/16.
 */

public interface WebService {
    @GET("/api/temp/?page=1")
    Call<TemperatureResponse> getTemperatures();

    @FormUrlEncoded
    @POST("/api-token-auth/")
    Call<TokenResponse> getLoginToken(@Field("username") String username, @Field("password") String password);
}
