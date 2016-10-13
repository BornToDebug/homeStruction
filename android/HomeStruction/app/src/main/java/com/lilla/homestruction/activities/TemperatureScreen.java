package com.lilla.homestruction.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.lilla.homestruction.R;
import com.lilla.homestruction.bean.Temperature;
import com.lilla.homestruction.interfaces.WebService;
import com.lilla.homestruction.managers.RetrofitManager;
import com.lilla.homestruction.preferences.SaveSharedPreference;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.theappguruz.imagezoom.ImageViewTouch;;import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by lilla on 22/09/16.
 * http://www.theappguruz.com/blog/android-pinch-zoom
 */

public class TemperatureScreen extends AppCompatActivity {

    private ImageViewTouch imageViewTouch;
    private ImageView imageView;
    private Bitmap myBitmap;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_screen);
        final String token = SaveSharedPreference.getToken(TemperatureScreen.this);

        String Url = "http://homestruction.servebeer.com/androidimage/?image=temp";

        imageView = (ImageView) findViewById(R.id.temperature);
        GlideUrl glideUrl = new GlideUrl(Url,
                new LazyHeaders.Builder()
                        .addHeader("Authorization", token).build());
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



