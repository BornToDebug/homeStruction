package com.lilla.homestruction.interfaces;

import com.lilla.homestruction.bean.DoorLockedResponse;
import com.lilla.homestruction.bean.DoorResponse;
import com.lilla.homestruction.bean.HumidityResponse;
import com.lilla.homestruction.bean.Lamp1Response;
import com.lilla.homestruction.bean.Lamp2Response;
import com.lilla.homestruction.bean.Lamp3Response;
import com.lilla.homestruction.bean.LatestData;
import com.lilla.homestruction.bean.LightResponse;
import com.lilla.homestruction.bean.TemperatureResponse;
import com.lilla.homestruction.bean.TokenResponse;
import com.lilla.homestruction.bean.WindowsResponse;

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
    //Webservice for temperatures
    @GET("/api/temp/?page=1")
    Call<TemperatureResponse> getTemperatures();

    //Webservice for login
    @FormUrlEncoded
    @POST("/api-token-auth/")
    Call<TokenResponse> getLoginToken(@Field("username") String username, @Field("password") String password);

    //Webservice for luminosity
    @GET("/api/light/")
    Call<LightResponse> getLight();

    //Webservice for lamp1 (the chandelier)
    @GET("/api/lamp/1")
    Call<Lamp1Response> getLamp1();

    //Webservice for lamp2 (the nightlight)
    @GET("/api/lamp/2")
    Call<Lamp2Response> getLamp2();

    //Webservice for the coffee machine/fan
    @GET("/api/lamp/3")
    Call<Lamp3Response> getLamp3();

    //Webservice for getting the door's state (open/closed)
    @GET("/api/door/")
    Call<DoorResponse> getDoor();

    //Webservice for the lock's state (door locked/unlocked)
    @GET("/api/lamp/d")
    Call<DoorLockedResponse> getDoorLocked();

    //Webservice for the windows (open/closed)
    @GET("/api/window/")
    Call<WindowsResponse> getWindows();

    //Webservice for humidity
    @GET("/api/humidity/")
    Call<HumidityResponse> getHumidity();

    //Webservice for sending commands (for the previous services)
    @GET("/androidcommand/")
    Call<ResponseBody> sendCommand(@Query("command") String command);

    //Webservice for resetting the alarm
    @GET("/androidsetalarm/")
    Call<ResponseBody> resetAlarm();

    //Webservice for setting the alarm
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

    @GET("/androidimage/")
    @Streaming  Call<ResponseBody> getImage(
            @Query("image") String command
    );

    @GET("/latestdata/")
    Call<LatestData> getLatestData();
}
