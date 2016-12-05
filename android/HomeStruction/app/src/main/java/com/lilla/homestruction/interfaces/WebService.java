package com.lilla.homestruction.interfaces;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.lilla.homestruction.bean.LatestData;
import com.lilla.homestruction.bean.TokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by lilla on 24/09/16.
 */

public interface WebService {
    /**Webservice for login**/
    @FormUrlEncoded
    @POST("/api-token-auth/")
    Call<TokenResponse> getLoginToken(@Field("username") String username, @Field("password") String password);

    /**Webservice for sending commands (for the previous services)**/
    @GET("/androidcommand/")
    Call<ResponseBody> sendCommand(@Query("command") String command);

    @GET("/stream/androidstart/")
    Call<ResponseBody> startStream();

    @GET("/stream/androidstop/")
    Call<ResponseBody> stopStream();

    /**Webservice for resetting the alarm**/
    @GET("/androidsetalarm/")
    Call<ResponseBody> resetAlarm();

    /**Webservice for setting the alarm**/
    @GET("/androidsetalarm/")
    Call<ResponseBody> sendAlarm(
            @Query("hour") String hour,
            @Query("minute") String minute,
            @Query("monday") String monday,
            @Query("tuesday") String tuesday,
            @Query("wednesday") String wednesday,
            @Query("thursday") String thursday,
            @Query("friday") String friday,
            @Query("saturday") String saturday,
            @Query("sunday") String sunday);

    /**Webservice for getting the graphs for temperature, luminosity and humidity**/
    @GET("/androidimage/")
    @Streaming  Call<ResponseBody> getImage(
            @Query("image") String command
    );

    /**Webservice for getting the latest data**/
    @GET("/latestdata/")
    Call<LatestData> getLatestData();
}
