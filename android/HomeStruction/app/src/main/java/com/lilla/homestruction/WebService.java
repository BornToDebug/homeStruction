package com.lilla.homestruction;

import com.lilla.homestruction.bean.DoorResponse;
import com.lilla.homestruction.bean.HumidityResponse;
import com.lilla.homestruction.bean.Lamp1Response;
import com.lilla.homestruction.bean.Lamp2Response;
import com.lilla.homestruction.bean.Lamp3Response;
import com.lilla.homestruction.bean.LightResponse;
import com.lilla.homestruction.bean.TemperatureResponse;
import com.lilla.homestruction.bean.TokenResponse;
import com.lilla.homestruction.bean.WindowsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("/api/light/")
    Call<LightResponse> getLight();

    @GET("/api/lamp/1")
    Call<Lamp1Response>  getLamp1();

    @GET("/api/lamp/2")
    Call<Lamp2Response>  getLamp2();

    @GET("/api/lamp/3")
    Call<Lamp3Response>  getLamp3();

    @GET("/api/door/")
    Call<DoorResponse> getDoor();

    @GET("/api/window/")
    Call<WindowsResponse> getWindows();

    @GET("/api/humidity/")
    Call<HumidityResponse> getHumidity();

    @GET("/androidcommand/")
    Call<String> sendCommand(@Query("command") String command);
}
