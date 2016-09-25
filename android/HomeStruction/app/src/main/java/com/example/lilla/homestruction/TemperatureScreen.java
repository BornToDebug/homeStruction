package com.example.lilla.homestruction;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

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
