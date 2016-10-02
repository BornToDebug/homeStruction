package com.lilla.homestruction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by lilla on 22/09/16.
 */

public class TemperatureScreen extends AppCompatActivity {

//    ImageView imageView;
//    Matrix matrix = new Matrix();
//    float scale = 1f;
//    ScaleGestureDetector SGD;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_screen);

//        ImageView imageView = (ImageView) findViewById(R.id.graph);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        Drawable drawable = getResources().getDrawable(R.drawable.graph);
//
//        int hMargin = (int) (displayMetrics.widthPixels * .10);
//        int vMargin = (int) (displayMetrics.heightPixels * .10);
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(displayMetrics.widthPixels - (hMargin * 2), (int)(displayMetrics.heightPixels) - (vMargin * 2));
//        params.leftMargin = hMargin;
//        params.topMargin =  vMargin;
//        imageView.setLayoutParams(params);
//        imageView.setImageDrawable(drawable);

        //TODO zoom in on the picture
//        imageView = (ImageView) findViewById(R.id.graph);
//        SGD = new ScaleGestureDetector(this, new ScaleListener());
    }

//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
//
//        public boolean onScale(ScaleGestureDetector detector){
//            scale = scale * detector.getScaleFactor();
//            scale = Math.max(0.1f, Math.min(scale, 5f));
//            matrix.setScale(scale, scale);
//            imageView.setImageMatrix(matrix);
//            return true;
//        }
//    }
//
//    public boolean onTouchEvent(MotionEvent event){
//        SGD.onTouchEvent(event);
//        return true;
//    }

}
