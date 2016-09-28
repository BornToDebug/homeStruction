package com.lilla.homestruction;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lilla.homestruction.bean.Door;
import com.lilla.homestruction.bean.DoorResponse;
import com.lilla.homestruction.bean.Lamp;
import com.lilla.homestruction.bean.LampResponse;
import com.lilla.homestruction.bean.Light;
import com.lilla.homestruction.bean.LightResponse;
import com.lilla.homestruction.bean.Temperature;
import com.lilla.homestruction.bean.TemperatureResponse;

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
    private TextView lightText;
    private Switch doorSwitch;



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
        findViewById(R.id.multimedia).setOnClickListener(this);
        findViewById(R.id.doors).setOnClickListener(this);
        findViewById(R.id.windows).setOnClickListener(this);
        findViewById(R.id.light).setOnClickListener(this);
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
        doorSwitch.setClickable(false);

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

//        final Button multimediaButton = (Button) findViewById(R.id.multimedia);
//        multimediaButton.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onClick(View view) {
//
//                System.out.println("Multimedia button clicked");
//
//                //TODO solve the ripple effect
//                //TODO make a music player (with buttons and song titles)
//
//
//                ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
//                    @Override
//                    public void getOutline(View view, Outline outline) {
//                        int width = multimediaButton.getWidth();
//                        int height = multimediaButton.getHeight();
//                        // Or read size directly from the view's width/height
//                        outline.setRoundRect(0, 0, width, height, R.dimen.radius);
//                    }
//                };
//                multimediaButton.setOutlineProvider(viewOutlineProvider);
//                multimediaButton.setClipToOutline(true);
//                int[] attrs = new int[]{R.attr.selectableItemBackground};
//                TypedArray typedArray = MainScreen.this.obtainStyledAttributes(attrs);
//                int backgroundResource = typedArray.getResourceId(0, 0);
//                view.setBackgroundResource(backgroundResource);
//            }
//        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        updateTemperatureData();
        updateLampData();
        updateDoorData();
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
                System.out.println("ddd Error: " + t.getMessage());
            }
        });
    }

    private void updateLampData() {
        WebService webService = RetrofitManager.createService(WebService.class,"Token " + SaveSharedPreference.getToken(MainScreen.this));
        Call<LampResponse> call = webService.getLamp();
        call.enqueue(new Callback<LampResponse>() {
            @Override
            public void onResponse(Call<LampResponse> call, Response<LampResponse> response) {
                List<Lamp> lampValues = response.body().getResults();
                if (lampValues.get(0) != null){

                }
            }

            @Override
            public void onFailure(Call<LampResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
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
                            doorSwitch.setChecked(true);
                            doorSwitch.setText("");
                            break;
                        case "closed":
                            doorSwitch.setChecked(false);
                            doorSwitch.setText("");
                            break;
                        default:
                            doorSwitch.setText("Doors: error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DoorResponse> call, Throwable t) {
                System.out.println("ddd Error: " + t.getMessage());
                doorSwitch.setText("Doors: error");
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
            case R.id.multimedia:
                System.out.println("Multimedia button clicked");

                //TODO solve the ripple effect
                //TODO make a music player (with buttons and song titles)
                break;
            case R.id.doors:
                System.out.println("Doors button clicked");
                break;
            case R.id.windows:
                System.out.println("Windows button clicked");
                break;
            case R.id.light:
                System.out.println("Light button clicked");
                Intent lightScreen = new Intent(MainScreen.this, LightScreen.class);
                startActivity(lightScreen);
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
                //THIS IS FUCKING AWESOME!!! :D
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


