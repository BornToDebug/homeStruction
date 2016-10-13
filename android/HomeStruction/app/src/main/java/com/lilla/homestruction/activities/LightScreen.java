package com.lilla.homestruction.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lilla.homestruction.R;
import com.theappguruz.imagezoom.ImageViewTouch;

/**
 * Created by lilla on 28/09/16.
 */

public class LightScreen extends AppCompatActivity {

    private ImageViewTouch imageViewTouch;
    private Bitmap myBitmap;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_screen);
        imageViewTouch = (ImageViewTouch) findViewById(R.id.graph);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.luminosity, options);
        if (options.outWidth > 3000 || options.outHeight > 2000) {
            options.inSampleSize = 4;
        } else if (options.outWidth > 2000 || options.outHeight > 1500) {
            options.inSampleSize = 3;
        } else if (options.outWidth > 1000 || options.outHeight > 1000) {
            options.inSampleSize = 2;
        }
        options.inJustDecodeBounds = false;

        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.luminosity, options);
        imageViewTouch.setImageBitmapReset(myBitmap, 0, true);
    }
}
