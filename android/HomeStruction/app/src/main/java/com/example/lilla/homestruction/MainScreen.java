package com.example.lilla.homestruction;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lilla.homestruction.bean.Temperature;
import com.example.lilla.homestruction.bean.TemperatureResponse;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lilla on 21/09/16.
 */

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView temperatureValue;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        if (SaveSharedPreference.getUserName(MainScreen.this).length() == 0) {
            Intent intent = new Intent(MainScreen.this, LoginActivity.class);
            startActivity(intent);
        }

        temperatureValue = (TextView) findViewById(R.id.temperature_value);
        System.out.println(SaveSharedPreference.getUserName(MainScreen.this));

//        WebService webService = WebService.retrofit.create(WebService.class);
//        final Call<List<Temperatures>> call = webService.getTemperatures("value");
//
//        call.enqueue(new Callback<List<Temperatures>>() {
//            @Override
//            public void onResponse(Call<List<Temperatures>> call, retrofit2.Response<List<Temperatures>> response) {
//                final TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Temperatures>> call, Throwable t) {
//                final TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText("Something went wrong: " + t.getMessage());
//            }
//        });

//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://acs1.ddns.net:2080/api/temp/?page=2";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        int ind = response.indexOf("value");
//                        ind = ind + 7;
//                        String temp = response.substring(ind, ind + 4);
//                        System.out.println(temp);
//                        //TODO fix this because on other devices it looks like shit
//                        temperatureButton.setText("     Temperature                                      " + temp);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                temperatureButton.setText("     Temperature                                no data");
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);


        findViewById(R.id.temperature).setOnClickListener(this);

        final Button multimediaButton = (Button) findViewById(R.id.multimedia);
        multimediaButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                System.out.println("Multimedia button clicked");

                //TODO solve the ripple effect
                //TODO make a music player (with buttons and song titles)


                ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        int width = multimediaButton.getWidth();
                        int height = multimediaButton.getHeight();
                        // Or read size directly from the view's width/height
                        outline.setRoundRect(0, 0, width, height, R.dimen.radius);
                    }
                };
                multimediaButton.setOutlineProvider(viewOutlineProvider);
                multimediaButton.setClipToOutline(true);
                int[] attrs = new int[]{R.attr.selectableItemBackground};
                TypedArray typedArray = MainScreen.this.obtainStyledAttributes(attrs);
                int backgroundResource = typedArray.getResourceId(0, 0);
                view.setBackgroundResource(backgroundResource);
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

        final Switch alarmButton = (Switch) findViewById(R.id.alarm);
        alarmButton.setChecked(false);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //TODO make a TimePicker!
            public void onClick(View view) {
                System.out.println("Alarm button clicked");
                if (alarmButton.isChecked()) {
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    final Calendar calendar = Calendar.getInstance();
                    //final TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);

                    TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            //timePicker.setHour(hourOfDay);
                            //timePicker.setMinute(minute);

                        }
                    };
                    //TODO: nem tudom kicserelni a szineit a timepickernek mert luzer vagyok
                    TimePickerDialog timePickerDialog = new TimePickerDialog(MainScreen.this, R.style.DialogTheme, timePickerListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        updateTemperatureData();
    }

    //TODO personalize your own settings in the settings menu

    private void updateTemperatureData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<TemperatureResponse> call = webService.getTemperatures();
        call.enqueue(new Callback<TemperatureResponse>() {
            @Override
            public void onResponse(Call<TemperatureResponse> call, Response<TemperatureResponse> response) {
                List<Temperature> temperatures = response.body().getResults();
                if (temperatures.get(0) != null){
                    temperatureValue.setText("" + temperatures.get(0).getValue()+ " °C");
                }
            }

            @Override
            public void onFailure(Call<TemperatureResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
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
                SaveSharedPreference.removeUserName(MainScreen.this);
                SaveSharedPreference.removeToken(MainScreen.this);
                Intent intent = new Intent(MainScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MainScreen Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.temperature:
                System.out.println("Temperature button clicked");
                Intent intent = new Intent(MainScreen.this, TemperatureScreen.class);
                startActivity(intent);
                break;
        }
    }
}


