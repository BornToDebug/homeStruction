package com.example.lilla.homestruction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by lilla on 21/09/16.
 */

public class MainScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        final Button temperatureButton = (Button) findViewById(R.id.temperature);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://acs1.ddns.net:2080/api/temp/?page=2";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        int ind = response.indexOf("value");
                        ind = ind + 7;
                        String temp = response.substring(ind, ind + 4);
                        System.out.println(temp);
                        temperatureButton.setText("     Temperature                                      " + temp);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                temperatureButton.setText("     Temperature                                             xx.x");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

        temperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Temperature button clicked");
                Intent intent = new Intent(MainScreen.this, TemperatureScreen.class);
                startActivity(intent);
            }
        });

        Button multimediaButton = (Button) findViewById(R.id.multimedia);
        multimediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Multimedia button clicked");
            }
        });

        Switch doorsButton = (Switch) findViewById(R.id.doors);
        doorsButton.setChecked(false);
        doorsButton.setOnClickListener(new View.OnClickListener() {
            boolean doorState = false;
            @Override
            public void onClick(View view) {
                System.out.println("Doors button clicked");
                doorState = !doorState;
                if (doorState) {
                    System.out.println("The door is open");
                } else {
                    System.out.println("The door is closed");
                }
            }
        });

        Switch windowsButton = (Switch) findViewById(R.id.windows);
        windowsButton.setChecked(false);

        windowsButton.setOnClickListener(new View.OnClickListener() {
            boolean windowState = false;
            @Override
            public void onClick(View view) {
                System.out.println("Windows button clicked");
                windowState = !windowState;
                if (windowState) {
                    System.out.println("The window is open");
                } else {
                    System.out.println("The window is closed");
                }
            }
        });

        Switch lightButton = (Switch) findViewById(R.id.light);
        lightButton.setChecked(false);
        lightButton.setOnClickListener(new View.OnClickListener() {
            boolean lightState = false;
            @Override
            public void onClick(View view) {
                System.out.println("Light button clicked");
                lightState = !lightState;
                if (lightState) {
                    System.out.println("The light is on");
                } else {
                    System.out.println("The light is off");
                }
            }
        });

        Button alarmButton = (Button) findViewById(R.id.alarm);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Alarm button clicked");
            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.sign_out:
                System.out.println("Sign out button pressed");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
