package com.lilla.homestruction.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.lilla.homestruction.R;
import com.lilla.homestruction.preferences.SaveSharedPreference;
import com.theappguruz.imagezoom.ImageViewTouch;


/**
 * Created by lilla on 22/09/16.
 * http://www.theappguruz.com/blog/android-pinch-zoom
 */

public class TemperatureScreen extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_screen);
        final String token = SaveSharedPreference.getToken(TemperatureScreen.this);
        Log.d("tok", token);
        ImageView imageView = (ImageView) findViewById(R.id.temperature_view);
        GlideUrl glideUrl = new GlideUrl("http://192.168.0.103/androidimage/?image=temp",
                new LazyHeaders.Builder()
                        .addHeader("Authorization", "Token " + token).build());
        Glide
                .with(this)
                .load(glideUrl)
                .into(imageView);
    }


//        imageViewTouch = (ImageViewTouch) findViewById(R.id.graph);


//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//
//        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.temperature, options);
//        if (options.outWidth > 3000 || options.outHeight > 2000) {
//            options.inSampleSize = 4;
//        } else if (options.outWidth > 2000 || options.outHeight > 1500) {
//            options.inSampleSize = 3;
//        } else if (options.outWidth > 1000 || options.outHeight > 1000) {
//            options.inSampleSize = 2;
//        }
//        options.inJustDecodeBounds = false;
//
//        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.temperature, options);
//        imageViewTouch.setImageBitmapReset(myBitmap, 0, true);
}



