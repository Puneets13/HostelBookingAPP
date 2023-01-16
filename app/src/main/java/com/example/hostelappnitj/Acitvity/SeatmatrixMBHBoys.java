package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.pm.ActivityInfo;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.views.interfaces.GestureView;
import com.example.hostelappnitj.R;

public class SeatmatrixMBHBoys extends AppCompatActivity {
AppCompatButton room301;
//ScrollView scrollView ;
//    private float mScale = 1f;
//    private ScaleGestureDetector mScaleGestureDetector;
//    GestureDetector gestureDetector;
    GestureView gestureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatmatrix_mbhboys);
        room301=findViewById(R.id.room301);
//        scrollView=findViewById(R.id.scrollView);
        room301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeatmatrixMBHBoys.this, "Room 301", Toast.LENGTH_SHORT).show();
            }
        });

//    zoom in the ScrollView
//        gestureDetector = new GestureDetector(this, new GestureListener());
//
//        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
//            @Override
//            public boolean onScale(ScaleGestureDetector detector) {
//                float scale = 1 - detector.getScaleFactor();
//                float prevScale = mScale;
//                mScale += scale;
//
//                if (mScale > 10f)
//                    mScale = 10f;
//
//                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
//                scaleAnimation.setDuration(0);
//                scaleAnimation.setFillAfter(true);
////                ScrollView layout = (ScrollView) findViewById(R.id.scrollView);
//                LinearLayout layout1=(LinearLayout)findViewById(R.id.linearMatrix);
//                layout1.startAnimation(scaleAnimation);
//                return true;
//            }
//        });
        gestureView=findViewById(R.id.gestureview);
        gestureView.getController().getSettings()
                .setMaxZoom(2f)
                .setDoubleTapZoom(-1f) // Falls back to max zoom level
                .setPanEnabled(true)
                .setZoomEnabled(true)
                .setDoubleTapEnabled(true)
                .setRotationEnabled(true)
                .setRestrictRotation(false)
                .setOverscrollDistance(0f, 0f)
                .setOverzoomFactor(2f)
                .setFillViewport(false)
                .setFitMethod(Settings.Fit.INSIDE)
                .setGravity(Gravity.CENTER);


    }
//to zoom the scrollView when touched
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        super.dispatchTouchEvent(event);
//        mScaleGestureDetector.onTouchEvent(event);
//        gestureDetector.onTouchEvent(event);
//        return gestureDetector.onTouchEvent(event);
//    }
//
//    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {
//            return true;
//        }
//    }

}