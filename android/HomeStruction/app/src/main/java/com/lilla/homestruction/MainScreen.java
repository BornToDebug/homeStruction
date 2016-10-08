package com.lilla.homestruction;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
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
import android.widget.TimePicker;

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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lilla on 21/09/16.
 */

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private CoordinatorLayout coordinatorLayout;
    private TextView temperatureValue;
    private TextView humidityValue;
    private TextView luminosityValue;
    private Switch doorSwitch;
    private TextView doorText;
    private ImageView windowOpen;
    private ImageView windowClosed;
    private TextView windowError;
    private Switch chandelierSwitch;
    private Switch nightLampSwitch;
    private Switch veCofSwitch;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        final SwipeRefreshLayout layout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        layout.setOnRefreshListener(refreshListener);


        if (SaveSharedPreference.getUserName(MainScreen.this).length() == 0) {
            Intent intent = new Intent(MainScreen.this,  LoginActivity.class);
            startActivity(intent);
        }
        temperatureValue = (TextView) findViewById(R.id.temperature_value);
        humidityValue = (TextView) findViewById(R.id.humidity_value);
        luminosityValue = (TextView) findViewById(R.id.luminosity_value);
        System.out.println(SaveSharedPreference.getUserName(MainScreen.this));
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        findViewById(R.id.temperature).setOnClickListener(this);
        findViewById(R.id.humidity).setOnClickListener(this);
        findViewById(R.id.luminosity).setOnClickListener(this);
        findViewById(R.id.multimedia).setOnClickListener(this);
        findViewById(R.id.doors).setOnClickListener(this);
        findViewById(R.id.doors_switch).setOnClickListener(this);
        findViewById(R.id.windows).setOnClickListener(this);
        findViewById(R.id.chandelier).setOnClickListener(this);
        findViewById(R.id.chandelier_switch).setOnClickListener(this);
        findViewById(R.id.nightlight).setOnClickListener(this);
        findViewById(R.id.nightlight_switch).setOnClickListener(this);
        findViewById(R.id.vecof).setOnClickListener(this);
        findViewById(R.id.vecof_switch).setOnClickListener(this);
        findViewById(R.id.alarm).setOnClickListener(this);
        findViewById(R.id.alarm_switch).setOnClickListener(this);
        findViewById(R.id.songs).setOnClickListener(this);
        findViewById(R.id.previous).setOnClickListener(this);
        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);
        findViewById(R.id.shuffle).setOnClickListener(this);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        final TextView volume = (TextView) findViewById(R.id.volume);
        doorSwitch = (Switch) findViewById(R.id.doors_switch);
        doorText = (TextView) findViewById(R.id.doors_text);
        windowOpen = (ImageView) findViewById(R.id.window_open);
        windowClosed = (ImageView) findViewById(R.id.window_closed);
        windowError = (TextView) findViewById(R.id.window_error);
        chandelierSwitch = (Switch) findViewById(R.id.chandelier_switch);
        nightLampSwitch = (Switch) findViewById(R.id.nightlight_switch);
        veCofSwitch = (Switch) findViewById(R.id.vecof_switch);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            public void onStopTrackingTouch(SeekBar bar)
            {
                int value = bar.getProgress(); // the value of the seekBar progress
            }

            public void onStartTrackingTouch(SeekBar bar)
            {

            }

            public void onProgressChanged(SeekBar bar,
                                          int paramInt, boolean paramBoolean)
            {
                volume.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        });

                //TODO solve the ripple effect

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        updateTemperatureData();
        updateLamp1Data();
        updateLamp2Data();
        updateLamp3Data();
        updateDoorData();
        updateDoorLockedData();
        updateWindowsData();
        updateHumidityData();
        updateLightData();
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener(){
        @Override
        public void onRefresh() {
            Intent refresh = getIntent();
            finish();
            startActivity(refresh);
        }
    };

    public void showSnackbar(){
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
                temperatureValue.setText("no data");
                showSnackbar();
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }



    private void updateHumidityData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<HumidityResponse> call = webService.getHumidity();
        call.enqueue(new Callback<HumidityResponse>() {
            @Override
            public void onResponse(Call<HumidityResponse> call, Response<HumidityResponse> response) {
                List<Humidity> humidity = response.body().getResults();
                if (humidity.get(0) != null){
                    humidityValue.setText("" + humidity.get(0).getValue()+ " %");
                }
                else {
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

    private void updateLightData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<LightResponse> call = webService.getLight();
        call.enqueue(new Callback<LightResponse>() {
            @Override
            public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
                List<Light> light = response.body().getResults();
                if (light.get(0) != null){
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


    private void updateLamp1Data() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<Lamp1Response> call = webService.getLamp1();
        call.enqueue(new Callback<Lamp1Response>() {
            @Override
            public void onResponse(Call<Lamp1Response> call, Response<Lamp1Response> response) {
                List<Lamp1> lamp1Values = response.body().getResults();
                if (lamp1Values.get(0) != null){
                    switch (lamp1Values.get(0).getValue()){
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

    private void updateLamp2Data() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<Lamp2Response> call = webService.getLamp2();
        call.enqueue(new Callback<Lamp2Response>() {
            @Override
            public void onResponse(Call<Lamp2Response> call, Response<Lamp2Response> response) {
                List<Lamp2> lamp2Values = response.body().getResults();
                if (lamp2Values.get(0) != null){
                    switch (lamp2Values.get(0).getValue()){
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

    private void updateLamp3Data() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<Lamp3Response> call = webService.getLamp3();
        call.enqueue(new Callback<Lamp3Response>() {
            @Override
            public void onResponse(Call<Lamp3Response> call, Response<Lamp3Response> response) {
                List<Lamp3> lamp3Values = response.body().getResults();
                if (lamp3Values.get(0) != null){
                    switch (lamp3Values.get(0).getValue()){
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

    private void updateDoorData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<DoorResponse> call = webService.getDoor();
        call.enqueue(new Callback<DoorResponse>() {
            @Override
            public void onResponse(Call<DoorResponse> call, Response<DoorResponse> response) {
                List<Door> doorValues = response.body().getResults();
                if (doorValues.get(0) != null){
                    switch (doorValues.get(0).getValue()){
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

    private void updateDoorLockedData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<DoorLockedResponse> call = webService.getDoorLocked();
        call.enqueue(new Callback<DoorLockedResponse>() {
            @Override
            public void onResponse(Call<DoorLockedResponse> call, Response<DoorLockedResponse> response) {
                List<DoorLocked> doorLockValues = response.body().getResults();
                if (doorLockValues.get(0) != null){
                    switch (doorLockValues.get(0).getValue()){
                        case "do_c":
                            doorSwitch.setChecked(true);
                            doorSwitch.setText("");
                            break;
                        case "do_uc":
                            doorSwitch.setChecked(true);
                            doorSwitch.setText("?");
                            break;
                        case "dc_c":
                            doorSwitch.setChecked(false);
                            doorSwitch.setText("");
                            break;
                        case "dc_uc":
                            doorSwitch.setChecked(false);
                            doorSwitch.setText("?");
                            break;
                        default:
                            doorSwitch.setText("error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DoorLockedResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                doorSwitch.setText("error");
            }
        });
    }

    private void updateWindowsData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<WindowsResponse> call = webService.getWindows();
        call.enqueue(new Callback<WindowsResponse>() {
            @Override
            public void onResponse(Call<WindowsResponse> call, Response<WindowsResponse> response) {
                List<Windows> windowValues = response.body().getResults();
                if (windowValues.get(0) != null){
                    switch (windowValues.get(0).getValue()){
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
                }
                else {
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

    public void sendToServer(String command){
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<ResponseBody> call = webService.sendCommand(command);
        final String myCommand = command;
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String myResponse= null;
                try {
                    myResponse = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("resp: " + myResponse + " command: " + myCommand);
                if (myResponse != null){
                    if (myCommand.equals(myResponse)) {
                        System.out.println("Success");
                    }
                    else {
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
        final Switch alarmSwitch = (Switch) findViewById(R.id.alarm_switch);
        ImageButton play = (ImageButton) findViewById(R.id.play);
        ImageButton pause = (ImageButton) findViewById(R.id.pause);

        switch (v.getId()){
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

                //TODO solve the ripple effect
                break;
            case R.id.doors:
                doorSwitch.toggle();
                if (doorSwitch.isChecked()){
                    System.out.println("ddd DoorSwitch checked");
                    sendToServer("opendoor");
                }
                else{
                    System.out.println("ddd DoorSwitch unchecked");
                    sendToServer("closedoor");
                }
                break;
            case R.id.doors_switch:
                if (doorSwitch.isChecked()){
                    System.out.println("ddd DoorSwitch checked");
                    sendToServer("opendoor");
                }
                else{
                    System.out.println("ddd DoorSwitch unchecked");
                    sendToServer("closedoor");
                }x
                break;
            case R.id.windows:
                System.out.println("Windows button clicked");
                break;
            case R.id.chandelier:
                chandelierSwitch.toggle();
                if (chandelierSwitch.isChecked()){
                    System.out.println("ddd ChandelierSwitch checked");
                    sendToServer("1lampon");
                }
                else{
                    System.out.println("ddd ChandelierSwitch unchecked");
                    sendToServer("1lampoff");
                }
                break;
            case R.id.chandelier_switch:
                if (chandelierSwitch.isChecked()){
                    System.out.println("ddd ChandelierSwitch checked");
                    sendToServer("1lampon");
                }
                else{
                    System.out.println("ddd ChandelierSwitch unchecked");
                    sendToServer("1lampoff");
                }
                break;
            case R.id.nightlight:
                nightLampSwitch.toggle();
                if (nightLampSwitch.isChecked()){
                    System.out.println("ddd NightLightSwitch checked");
                    sendToServer("2lampon");
                }
                else{
                    System.out.println("ddd NightLightSwitch unchecked");
                    sendToServer("2lampoff");
                }
                break;
            case R.id.nightlight_switch:
                if (nightLampSwitch.isChecked()){
                    System.out.println("ddd NightLightSwitch checked");
                    sendToServer("2lampon");
                }
                else{
                    System.out.println("ddd NightLightSwitch unchecked");
                    sendToServer("2lampoff");
                }
                break;
            case R.id.vecof:
                veCofSwitch.toggle();
                if (veCofSwitch.isChecked()) {
                    System.out.println("ddd VeCofSwitch checked");
                    sendToServer("3lampon");
                }
                else {
                    System.out.println("ddd VeCofSwitch unchecked");
                    sendToServer("3lampoff");
                }
                break;
            case R.id.vecof_switch:
                if (veCofSwitch.isChecked()) {
                    System.out.println("ddd VeCofSwitch checked");
                    sendToServer("3lampon");
                }
                else {
                    System.out.println("ddd VeCofSwitch unchecked");
                    sendToServer("3lampoff");
                }
                break;
            case R.id.alarm:
                System.out.println("Alarm button clicked");


                if (!alarmSwitch.isChecked()) {
                    alarmSwitch.toggle();
                }

                final Calendar calendar = Calendar.getInstance();
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);


                //TODO: nem tudom kicserelni a szineit a timepickernek mert luzer vagyok
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (hourOfDay < 10){
                                    if (minute < 10){
                                        alarmSwitch.setText("0" + hourOfDay + ":" + "0" + minute);
                                    }
                                    else {
                                        alarmSwitch.setText("0" + hourOfDay + ":" + minute);
                                    }
                                }
                                else {
                                    if (minute < 10){
                                        alarmSwitch.setText(hourOfDay + ":" + "0" + minute);
                                    }
                                    else {
                                        alarmSwitch.setText(hourOfDay + ":" + minute);
                                    }
                                }

                                System.out.println("SET alarm");


                            }
                        }, mHour, mMinute, true);

                timePickerDialog.show();
                break;
            case R.id.alarm_switch:

                if (!alarmSwitch.isChecked()){
                    alarmSwitch.setText("");
                    System.out.println("RESET alarm!");
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
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}


