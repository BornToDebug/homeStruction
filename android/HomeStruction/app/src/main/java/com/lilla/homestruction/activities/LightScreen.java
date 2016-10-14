package com.lilla.homestruction.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.lilla.homestruction.R;
import com.lilla.homestruction.preferences.SaveSharedPreference;
import com.squareup.picasso.Picasso;
import com.theappguruz.imagezoom.ImageViewTouch;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lilla on 28/09/16.
 */

public class LightScreen extends AppCompatActivity {

    private ImageViewTouch imageViewTouch;
    private Bitmap myBitmap;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_screen);
        final String token = SaveSharedPreference.getToken(LightScreen.this);
        imageViewTouch = (ImageViewTouch) findViewById(R.id.graph);
        String url = "http://homestruction.servebeer.com/androidimage/?image=light";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Token " + token); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(client))
                .build();

        picasso
                .load(url)
                .placeholder(R.drawable.graph_placeholder)
                .into(imageViewTouch);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.graph_placeholder, options);
        if (options.outWidth > 3000 || options.outHeight > 2000) {
            options.inSampleSize = 4;
        } else if (options.outWidth > 2000 || options.outHeight > 1500) {
            options.inSampleSize = 3;
        } else if (options.outWidth > 1000 || options.outHeight > 1000) {
            options.inSampleSize = 2;
        }
        options.inJustDecodeBounds = false;

        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.graph_placeholder, options);
        imageViewTouch.setImageBitmapReset(myBitmap, 0, true);
    }
}
