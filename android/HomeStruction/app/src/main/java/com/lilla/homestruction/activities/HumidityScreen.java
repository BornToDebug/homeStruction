package com.lilla.homestruction.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

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
 * Created by lilla on 05/10/16.
 */

public class HumidityScreen extends AppCompatActivity {
    private ImageViewTouch imageViewTouch;
    private Bitmap myBitmap;
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.humidity_screen);
        final String token = SaveSharedPreference.getToken(HumidityScreen.this);
        imageViewTouch = (ImageViewTouch) findViewById(R.id.graph);
        String url = "https://homestruction.org/androidimage/?image=humid";

        /**Create a http client with the authorization and the token**/
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                /** Request customization: add request headers **/
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Token " + token);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        /**Create a new picasso which downloads the picture from the http client**/
        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(client))
                .build();

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        /**Load the picture and place it on the placeholder**/
        picasso
                .load(url)
                .placeholder(R.drawable.graph_placeholder)
                .into(imageViewTouch, new com.squareup.picasso.Callback() {
                    /**Add progress bar if picture is not loaded yet**/
                    @Override
                    public void onSuccess() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        /**Use ImageViewTouch to pinch-zoom, double-click zoom and scroll the picture**/
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
