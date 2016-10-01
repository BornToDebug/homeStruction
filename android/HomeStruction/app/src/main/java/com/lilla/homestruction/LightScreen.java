package com.lilla.homestruction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lilla.homestruction.bean.Light;
import com.lilla.homestruction.bean.LightResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lilla on 28/09/16.
 */

public class LightScreen extends AppCompatActivity {

    private TextView luminosityValue;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_screen);

        luminosityValue = (TextView) findViewById(R.id.luminosity_value);
        updateLightData();
    }

    private void updateLightData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(LightScreen.this));
        Call<LightResponse> call = webService.getLight();
        call.enqueue(new Callback<LightResponse>() {
            @Override
            public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
                List<Light> light = response.body().getResults();
                if (light.get(0) != null){
                    luminosityValue.setText("Luminosity: " + light.get(0).getValue());
                }
            }

            @Override
            public void onFailure(Call<LightResponse> call, Throwable t) {
                luminosityValue.setText("Luminosity: no data");
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }
}
