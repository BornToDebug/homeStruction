package com.lilla.homestruction;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lilla.homestruction.bean.Door;
import com.lilla.homestruction.bean.DoorLocked;
import com.lilla.homestruction.bean.DoorLockedResponse;
import com.lilla.homestruction.bean.DoorResponse;
import com.lilla.homestruction.bean.Humidity;
import com.lilla.homestruction.bean.HumidityResponse;
import com.lilla.homestruction.bean.Lamp1;
import com.lilla.homestruction.bean.Lamp1Response;
import com.lilla.homestruction.bean.Lamp2;
import com.lilla.homestruction.bean.Lamp2Response;
import com.lilla.homestruction.bean.Lamp3;
import com.lilla.homestruction.bean.Lamp3Response;
import com.lilla.homestruction.bean.Light;
import com.lilla.homestruction.bean.LightResponse;
import com.lilla.homestruction.bean.Temperature;
import com.lilla.homestruction.bean.TemperatureResponse;
import com.lilla.homestruction.bean.Windows;
import com.lilla.homestruction.bean.WindowsResponse;
import com.lilla.homestruction.fragments.TimePickerFragment;
import com.lilla.homestruction.listeners.OnDialogCallbacksListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lilla on 21/09/16.
 */

public class MainScreen extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener, OnDialogCallbacksListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private CoordinatorLayout coordinatorLayout;
    private TextView temperatureValue;
    private TextView humidityValue;
    private TextView luminosityValue;
    private ImageView doorLocked;
    private ImageView doorUnlocked;
    private TextView doorText;
    private ImageView windowOpen;
    private ImageView windowClosed;
    private TextView windowError;
    private Switch chandelierSwitch;
    private Switch nightLampSwitch;
    private Switch veCofSwitch;
    private TextView confirm;
    private Switch alarmSwitch;
    private List<Boolean> daysChecked;
    private ToggleButton mondayButton;
    private ToggleButton tuesdayButton;
    private ToggleButton wednesdayButton;
    private ToggleButton thursdayButton;
    private ToggleButton fridayButton;
    private ToggleButton saturdayButton;
    private ToggleButton sundayButton;
    private String hour;
    private String minute;


    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        hour = null;
        minute = null;

        final SwipeRefreshLayout layout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        layout.setOnRefreshListener(refreshListener);


        if (SaveSharedPreference.getUserName(MainScreen.this).length() == 0) {
            Intent intent = new Intent(MainScreen.this, LoginActivity.class);
            startActivity(intent);
        }
        long startTime2 = System.currentTimeMillis();
        System.out.println("TEST first run: " + (startTime2 - startTime));

        temperatureValue = (TextView) findViewById(R.id.temperature_value);
        humidityValue = (TextView) findViewById(R.id.humidity_value);
        luminosityValue = (TextView) findViewById(R.id.luminosity_value);
        System.out.println(SaveSharedPreference.getUserName(MainScreen.this));
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        doorLocked = (ImageView) findViewById(R.id.door_locked);
        doorUnlocked = (ImageView) findViewById(R.id.door_unlocked);
        confirm = (TextView) findViewById(R.id.conf);
        alarmSwitch = (Switch) findViewById(R.id.alarm_switch);
        daysChecked = new ArrayList<>(Arrays.asList(new Boolean[7]));
        Collections.fill(daysChecked, Boolean.FALSE);
        mondayButton = (ToggleButton) findViewById(R.id.monday);
        tuesdayButton = (ToggleButton) findViewById(R.id.tuesday);
        wednesdayButton = (ToggleButton) findViewById(R.id.wednesday);
        thursdayButton = (ToggleButton) findViewById(R.id.thursday);
        fridayButton = (ToggleButton) findViewById(R.id.friday);
        saturdayButton = (ToggleButton) findViewById(R.id.saturday);
        sundayButton = (ToggleButton) findViewById(R.id.sunday);


        findViewById(R.id.temperature).setOnClickListener(this);
        findViewById(R.id.humidity).setOnClickListener(this);
        findViewById(R.id.luminosity).setOnClickListener(this);
        findViewById(R.id.multimedia).setOnClickListener(this);
        findViewById(R.id.doors).setOnClickListener(this);
        findViewById(R.id.lock).setOnClickListener(this);
        findViewById(R.id.windows).setOnClickListener(this);
        findViewById(R.id.chandelier).setOnClickListener(this);
        findViewById(R.id.chandelier_switch).setOnClickListener(this);
        findViewById(R.id.nightlight).setOnClickListener(this);
        findViewById(R.id.nightlight_switch).setOnClickListener(this);
        findViewById(R.id.vecof).setOnClickListener(this);
        findViewById(R.id.vecof_switch).setOnClickListener(this);
        findViewById(R.id.alarm).setOnClickListener(this);
        findViewById(R.id.alarm_switch).setOnClickListener(this);
        findViewById(R.id.monday).setOnClickListener(this);
        findViewById(R.id.tuesday).setOnClickListener(this);
        findViewById(R.id.wednesday).setOnClickListener(this);
        findViewById(R.id.thursday).setOnClickListener(this);
        findViewById(R.id.friday).setOnClickListener(this);
        findViewById(R.id.saturday).setOnClickListener(this);
        findViewById(R.id.sunday).setOnClickListener(this);
        findViewById(R.id.songs).setOnClickListener(this);
        findViewById(R.id.previous).setOnClickListener(this);
        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);
        findViewById(R.id.shuffle).setOnClickListener(this);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        final TextView volume = (TextView) findViewById(R.id.volume);
        doorText = (TextView) findViewById(R.id.doors_text);
        windowOpen = (ImageView) findViewById(R.id.window_open);
        windowClosed = (ImageView) findViewById(R.id.window_closed);
        windowError = (TextView) findViewById(R.id.window_error);
        chandelierSwitch = (Switch) findViewById(R.id.chandelier_switch);
        nightLampSwitch = (Switch) findViewById(R.id.nightlight_switch);
        veCofSwitch = (Switch) findViewById(R.id.vecof_switch);

        long startTime3 = System.currentTimeMillis();
        System.out.println("TEST finding views run: " + (startTime3 - startTime2));

        volume.setText("0%");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar bar) {
                int value = bar.getProgress(); // the value of the seekBar progress
            }

            public void onStartTrackingTouch(SeekBar bar) {

            }

            public void onProgressChanged(SeekBar bar,
                                          int paramInt, boolean paramBoolean) {
                volume.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        });

        //TODO solve the ripple effect

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        WebService webService = RetrofitManager.createService(WebService.class, "Token " + SaveSharedPreference.getToken(MainScreen.this));
        updateTemperatureData(webService);
        updateLamp1Data(webService);
        updateLamp2Data(webService);
        updateLamp3Data(webService);
        updateDoorData(webService);
        updateDoorLockedData(webService);
        updateWindowsData(webService);
        updateHumidityData(webService);
        updateLightData(webService);
        long startTime4 = System.currentTimeMillis();
        System.out.println("TEST first run updating shits : " + (startTime4 - startTime3));
        System.out.println("TEST first run total : " + (startTime4 - startTime));
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Intent refresh = getIntent();
            finish();
            startActivity(refresh);
        }
    };

    public void showSnackbar() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Failed to connect to server", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent refresh = getIntent();
                        finish();
                        startActivity(refresh);
                    }
                });
        snackbar.show();
    }

    //TODO personalize your own settings in the settings menu

    private void updateTemperatureData(WebService webService) {
        Call<TemperatureResponse> call = webService.getTemperatures();
        call.enqueue(new Callback<TemperatureResponse>() {
            @Override
            public void onResponse(Call<TemperatureResponse> call, Response<TemperatureResponse> response) {
                List<Temperature> temperatures = response.body().getResults();
                if (temperatures.get(0) != null) {
                    temperatureValue.setText("" + temperatures.get(0).getValue() + " °C");
                }
            }

            @Override
            public void onFailure(Call<TemperatureResponse> call, Throwable t) {
                temperatureValue.setText("no data");
                showSnackbar();
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }


    private void updateHumidityData(WebService webService) {
        Call<HumidityResponse> call = webService.getHumidity();
        call.enqueue(new Callback<HumidityResponse>() {
            @Override
            public void onResponse(Call<HumidityResponse> call, Response<HumidityResponse> response) {
                List<Humidity> humidity = response.body().getResults();
                if (humidity.get(0) != null) {
                    humidityValue.setText("" + humidity.get(0).getValue() + " %");
                } else {
                    humidityValue.setText("no data");
                }
            }

            @Override
            public void onFailure(Call<HumidityResponse> call, Throwable t) {
                humidityValue.setText("no data");
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }

    private void updateLightData(WebService webService) {
        Call<LightResponse> call = webService.getLight();
        call.enqueue(new Callback<LightResponse>() {
            @Override
            public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
                List<Light> light = response.body().getResults();
                if (light.get(0) != null) {
                    luminosityValue.setText("" + light.get(0).getValue());
                }
            }

            @Override
            public void onFailure(Call<LightResponse> call, Throwable t) {
                luminosityValue.setText("no data");
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }


    private void updateLamp1Data(WebService webService) {
        Call<Lamp1Response> call = webService.getLamp1();
        call.enqueue(new Callback<Lamp1Response>() {
            @Override
            public void onResponse(Call<Lamp1Response> call, Response<Lamp1Response> response) {
                List<Lamp1> lamp1Values = response.body().getResults();
                if (lamp1Values.get(0) != null) {
                    switch (lamp1Values.get(0).getValue()) {
                        case "1off_c":
                            chandelierSwitch.setChecked(false);
                            chandelierSwitch.setText("");
                            break;
                        case "1off_uc":
                            chandelierSwitch.setChecked(false);
                            chandelierSwitch.setText("?");
                            break;
                        case "1on_c":
                            chandelierSwitch.setChecked(true);
                            chandelierSwitch.setText("");
                            break;
                        case "1on_uc":
                            chandelierSwitch.setChecked(true);
                            chandelierSwitch.setText("?");
                            break;
                        default:
                            chandelierSwitch.setText("error");
                            break;
                    }
                }
                if (chandelierSwitch.isChecked()) {
                    System.out.println("Chandelier on");
                } else {
                    System.out.println("Chandelier off");
                }
            }

            @Override
            public void onFailure(Call<Lamp1Response> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                chandelierSwitch.setText("error");
            }
        });
    }

    private void updateLamp2Data(WebService webService) {
        Call<Lamp2Response> call = webService.getLamp2();
        call.enqueue(new Callback<Lamp2Response>() {
            @Override
            public void onResponse(Call<Lamp2Response> call, Response<Lamp2Response> response) {
                List<Lamp2> lamp2Values = response.body().getResults();
                if (lamp2Values.get(0) != null) {
                    switch (lamp2Values.get(0).getValue()) {
                        case "2off_c":
                            nightLampSwitch.setChecked(false);
                            nightLampSwitch.setText("");
                            break;
                        case "2off_uc":
                            nightLampSwitch.setChecked(false);
                            nightLampSwitch.setText("?");
                            break;
                        case "2on_c":
                            nightLampSwitch.setChecked(true);
                            nightLampSwitch.setText("");
                            break;
                        case "2on_uc":
                            nightLampSwitch.setChecked(true);
                            nightLampSwitch.setText("?");
                            break;
                        default:
                            nightLampSwitch.setText("error");
                            break;
                    }
                }
                if (nightLampSwitch.isChecked()) {
                    System.out.println("Nightlamp on");
                } else {
                    System.out.println("Nightlamp off");
                }
            }

            @Override
            public void onFailure(Call<Lamp2Response> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                nightLampSwitch.setText("error");
            }
        });
    }

    private void updateLamp3Data(WebService webService) {
        Call<Lamp3Response> call = webService.getLamp3();
        call.enqueue(new Callback<Lamp3Response>() {
            @Override
            public void onResponse(Call<Lamp3Response> call, Response<Lamp3Response> response) {
                List<Lamp3> lamp3Values = response.body().getResults();
                if (lamp3Values.get(0) != null) {
                    switch (lamp3Values.get(0).getValue()) {
                        case "3off_c":
                            veCofSwitch.setChecked(false);
                            veCofSwitch.setText("");
                            break;
                        case "3off_uc":
                            veCofSwitch.setChecked(false);
                            veCofSwitch.setText("?");
                            break;
                        case "3on_c":
                            veCofSwitch.setChecked(true);
                            veCofSwitch.setText("");
                            break;
                        case "3on_uc":
                            veCofSwitch.setChecked(true);
                            veCofSwitch.setText("?");
                            break;
                        default:
                            veCofSwitch.setText("error");
                            break;
                    }
                }
                if (veCofSwitch.isChecked()) {
                    System.out.println("Ventillator/coffee machine on");
                } else {
                    System.out.println("Ventillator/coffee machine off");
                }
            }

            @Override
            public void onFailure(Call<Lamp3Response> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                veCofSwitch.setText("error");
            }
        });
    }

    private void updateDoorData(WebService webService) {
        Call<DoorResponse> call = webService.getDoor();
        call.enqueue(new Callback<DoorResponse>() {
            @Override
            public void onResponse(Call<DoorResponse> call, Response<DoorResponse> response) {
                List<Door> doorValues = response.body().getResults();
                if (doorValues.get(0) != null) {
                    switch (doorValues.get(0).getValue()) {
                        case "opened":
                            doorText.setText("Door open");
                            break;
                        case "closed":
                            doorText.setText("Door closed");
                            break;
                        default:
                            doorText.setText("Door error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DoorResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                doorText.setText("Door error");
            }
        });
    }

    private void updateDoorLockedData(WebService webService) {
        Call<DoorLockedResponse> call = webService.getDoorLocked();
        call.enqueue(new Callback<DoorLockedResponse>() {
            @Override
            public void onResponse(Call<DoorLockedResponse> call, Response<DoorLockedResponse> response) {
                List<DoorLocked> doorLockValues = response.body().getResults();
                if (doorLockValues.get(0) != null) {
                    switch (doorLockValues.get(0).getValue()) {
                        case "do_c":
                            doorUnlocked.setVisibility(View.VISIBLE);
                            doorLocked.setVisibility(View.INVISIBLE);
                            confirm.setText("");
                            break;
                        case "do_uc":
                            doorUnlocked.setVisibility(View.VISIBLE);
                            doorLocked.setVisibility(View.INVISIBLE);
                            confirm.setText("?");
                            break;
                        case "dc_c":
                            doorUnlocked.setVisibility(View.INVISIBLE);
                            doorLocked.setVisibility(View.VISIBLE);
                            confirm.setText("");
                            break;
                        case "dc_uc":
                            doorUnlocked.setVisibility(View.INVISIBLE);
                            doorLocked.setVisibility(View.VISIBLE);
                            confirm.setText("?");
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DoorLockedResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }

    private void updateWindowsData(WebService webService) {
        Call<WindowsResponse> call = webService.getWindows();
        call.enqueue(new Callback<WindowsResponse>() {
            @Override
            public void onResponse(Call<WindowsResponse> call, Response<WindowsResponse> response) {
                List<Windows> windowValues = response.body().getResults();
                if (windowValues.get(0) != null) {
                    switch (windowValues.get(0).getValue()) {
                        case "opened":
                            windowOpen.setVisibility(View.VISIBLE);
                            windowClosed.setVisibility(View.INVISIBLE);
                            windowError.setVisibility(View.INVISIBLE);
                            System.out.println();
                            break;
                        case "closed":
                            windowOpen.setVisibility(View.INVISIBLE);
                            windowClosed.setVisibility(View.VISIBLE);
                            windowError.setVisibility(View.INVISIBLE);
                            break;
                        default:
                            windowOpen.setVisibility(View.INVISIBLE);
                            windowClosed.setVisibility(View.INVISIBLE);
                            windowError.setVisibility(View.VISIBLE);
                            break;
                    }
                } else {
                    windowOpen.setVisibility(View.INVISIBLE);
                    windowClosed.setVisibility(View.INVISIBLE);
                    windowError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<WindowsResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                windowOpen.setVisibility(View.INVISIBLE);
                windowClosed.setVisibility(View.INVISIBLE);
                windowError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void sendToServer(String command, WebService webService) {
        Call<ResponseBody> call = webService.sendCommand(command);
        final String myCommand = command;
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String myResponse = null;
                try {
                    myResponse = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("resp: " + myResponse + " command: " + myCommand);
                if (myResponse != null) {
                    if (myCommand.equals(myResponse)) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("dddd Error: " + t.getMessage());
                showSnackbar();
            }
        });
    }

    public void setAlarm(String hour, String minute, String monday, String tuesday, String wednesday, String thursday,
                         String friday, String saturday, String sunday, WebService webService) {
        Call<ResponseBody> call = webService.sendAlarm(hour, minute, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String myResponse = null;
                try {
                    myResponse = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("resp: " + myResponse );
                if (myResponse != null) {
                    if (myResponse.equals("success")) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("LOG Error: " + t.getMessage());
                showSnackbar();
            }
        });
    }

    public void resetAlarm(WebService webService) {
        Call<ResponseBody> call = webService.resetAlarm();
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String myResponse = null;
                try {
                    myResponse = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("resp: " + myResponse);
                if (myResponse != null) {
                    if (myResponse.equals("success")) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("LOG Error: " + t.getMessage());
                showSnackbar();
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

        ImageButton play = (ImageButton) findViewById(R.id.play);
        ImageButton pause = (ImageButton) findViewById(R.id.pause);
        WebService webService = RetrofitManager.createService(WebService.class, "Token " + SaveSharedPreference.getToken(MainScreen.this));

        switch (v.getId()) {
            case R.id.temperature:
                System.out.println("Temperature button clicked");
                Intent intent = new Intent(MainScreen.this, TemperatureScreen.class);
                startActivity(intent);
                break;
            case R.id.humidity:
                System.out.println("Humidity button clicked");
                Intent humid = new Intent(MainScreen.this, HumidityScreen.class);
                startActivity(humid);
                break;
            case R.id.luminosity:
                System.out.println("Luminosity button clicked");
                Intent lum = new Intent(MainScreen.this, LightScreen.class);
                startActivity(lum);
                break;
            case R.id.multimedia:
                System.out.println("Multimedia button clicked");
                break;
            case R.id.doors:
                System.out.println("Clicked doors button");
                if (doorLocked.getVisibility() == View.VISIBLE) {
                    doorLocked.setVisibility(View.INVISIBLE);
                    doorUnlocked.setVisibility(View.VISIBLE);
                    System.out.println("Door unlocked");
                    sendToServer("opendoor", webService);
                } else {
                    doorLocked.setVisibility(View.VISIBLE);
                    doorUnlocked.setVisibility(View.INVISIBLE);
                    System.out.println("Door locked");
                    sendToServer("closedoor", webService);
                }
                break;
            case R.id.lock:
                if (doorLocked.getVisibility() == View.VISIBLE) {
                    doorLocked.setVisibility(View.INVISIBLE);
                    doorUnlocked.setVisibility(View.VISIBLE);
                    System.out.println("Door unlocked");
                    sendToServer("opendoor", webService);
                } else {
                    doorLocked.setVisibility(View.VISIBLE);
                    doorUnlocked.setVisibility(View.INVISIBLE);
                    System.out.println("Door locked");
                    sendToServer("closedoor", webService);
                }
                break;
            case R.id.windows:
                System.out.println("Windows button clicked");
                break;
            case R.id.chandelier:
                chandelierSwitch.toggle();
                if (chandelierSwitch.isChecked()) {
                    System.out.println("ddd ChandelierSwitch checked");
                    sendToServer("1lampon", webService);
                } else {
                    System.out.println("ddd ChandelierSwitch unchecked");
                    sendToServer("1lampoff", webService);
                }
                break;
            case R.id.chandelier_switch:
                if (chandelierSwitch.isChecked()) {
                    System.out.println("ddd ChandelierSwitch checked");
                    sendToServer("1lampon", webService);
                } else {
                    System.out.println("ddd ChandelierSwitch unchecked");
                    sendToServer("1lampoff", webService);
                }
                break;
            case R.id.nightlight:
                nightLampSwitch.toggle();
                if (nightLampSwitch.isChecked()) {
                    System.out.println("ddd NightLightSwitch checked");
                    sendToServer("2lampon", webService);
                } else {
                    System.out.println("ddd NightLightSwitch unchecked");
                    sendToServer("2lampoff", webService);
                }
                break;
            case R.id.nightlight_switch:
                if (nightLampSwitch.isChecked()) {
                    System.out.println("ddd NightLightSwitch checked");
                    sendToServer("2lampon", webService);
                } else {
                    System.out.println("ddd NightLightSwitch unchecked");
                    sendToServer("2lampoff", webService);
                }
                break;
            case R.id.vecof:
                veCofSwitch.toggle();
                if (veCofSwitch.isChecked()) {
                    System.out.println("ddd VeCofSwitch checked");
                    sendToServer("3lampon", webService);
                } else {
                    System.out.println("ddd VeCofSwitch unchecked");
                    sendToServer("3lampoff", webService);
                }
                break;
            case R.id.vecof_switch:
                if (veCofSwitch.isChecked()) {
                    System.out.println("ddd VeCofSwitch checked");
                    sendToServer("3lampon", webService);
                } else {
                    System.out.println("ddd VeCofSwitch unchecked");
                    sendToServer("3lampoff", webService);
                }
                break;
            case R.id.alarm:
                System.out.println("Alarm button clicked");
                if (!alarmSwitch.isChecked()) {
                    alarmSwitch.toggle();
                }
                showTimePickerDialog();
                System.out.println("LOG " + hour + ":" + minute);
                break;
            case R.id.alarm_switch:
                if (!alarmSwitch.isChecked()) {
                    alarmSwitch.setText("");
                    System.out.println("RESET alarm!");
                    resetAlarm(webService);
                    //todo call resetalarm()
                } else {
                    showTimePickerDialog();
                }
                System.out.println("LOG " + hour + ":" + minute);
                break;
            case R.id.monday:
                if (mondayButton.isChecked()) {
                    daysChecked.set(0, true);
                } else {
                    daysChecked.set(0, false);
                }
                System.out.println("LOG monday button clicked");
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.tuesday:
                if (tuesdayButton.isChecked()) {
                    daysChecked.set(1, true);
                } else {
                    daysChecked.set(1, false);
                }
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.wednesday:
                if (wednesdayButton.isChecked()) {
                    daysChecked.set(2, true);
                } else {
                    daysChecked.set(2, false);
                }
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.thursday:
                if (thursdayButton.isChecked()) {
                    daysChecked.set(3, true);
                } else {
                    daysChecked.set(3, false);
                }
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.friday:
                if (fridayButton.isChecked()) {
                    daysChecked.set(4, true);
                } else {
                    daysChecked.set(4, false);
                }
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.saturday:
                if (saturdayButton.isChecked()) {
                    daysChecked.set(5, true);
                } else {
                    daysChecked.set(5, false);
                }
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.sunday:
                if (sundayButton.isChecked()) {
                    daysChecked.set(6, true);
                } else {
                    daysChecked.set(6, false);
                }
                if (hour != null || minute != null) {
                    createRequest(daysChecked, webService);
                }
                break;
            case R.id.songs:
                System.out.println("Songs button pressed");
                break;
            case R.id.previous:
                System.out.println("Previous button pressed");
                break;
            case R.id.play:
                System.out.println("Play button pressed");
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                break;
            case R.id.pause:
                System.out.println("Pause button pressed");
                play.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
                break;
            case R.id.next:
                System.out.println("Next button pressed");
                break;
            case R.id.shuffle:
                System.out.println("Shuffle button pressed");
                break;
        }
    }

    public void showTimePickerDialog() {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setOnDialogCallbacksListener(this);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onTimeSet(int hourOfDay, int minute) {
        System.out.println("BAZDMEG time set: " + hourOfDay + ":" + minute);
        hour = Integer.toString(hourOfDay);
        this.minute = Integer.toString(minute);
        if (hourOfDay < 10){
            if (minute < 10){
                hour = "0" + hour;
                this.minute = "0" + minute;
                alarmSwitch.setText("0" + hourOfDay + ":0" + minute);
            }
            else {
                hour = "0" + hour;
            }
        }
        else {
            if (minute < 10){
                this.minute = "0" + minute;
            }
        }
        alarmSwitch.setText(hour + ":" + this.minute);
    }

    @Override
    public void onCancel() {
        System.out.println("BAZDMEG canceled");
        System.out.println("LOG" + daysChecked);
        hour = null;
        minute = null;
    }

    public void createRequest(List<Boolean> daysChecked, WebService webService) {

        String request = hour + "&minute=" + minute;
        List<String> days = Arrays.asList(null,null,null,null,null,null,null);
        for (int i = 0; i < 7; i++) {
            if (daysChecked.get(i).equals(true)) {
                days.set(i, "True");
            }
        }
        System.out.println(days);
        setAlarm(hour, minute, days.get(0), days.get(1), days.get(2), days.get(3), days.get(4), days.get(5), days.get(6), webService);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }
}