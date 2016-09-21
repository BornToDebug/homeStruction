package com.example.lilla.homestruction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lilla on 21/09/16.
 */

public class MainScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Button temperatureButton = (Button) findViewById(R.id.temperature);
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

        Button doorsButton = (Button) findViewById(R.id.doors);
        doorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Doors button clicked");
            }
        });

        Button windowsButton = (Button) findViewById(R.id.windows);
        windowsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Windows button clicked");
            }
        });

        Button lightButton = (Button) findViewById(R.id.light);
        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Light button clicked");
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
}
