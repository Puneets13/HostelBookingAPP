package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.hostelappnitj.R;

public class SeatmatrixMBHBoys extends AppCompatActivity {
AppCompatButton room301;
//ScrollView scrollView ;
    private float mScale = 1f;
    private ScaleGestureDetector mScaleGestureDetector;
    GestureDetector gestureDetector;
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
        gestureDetector = new GestureDetector(this, new GestureListener());

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = 1 - detector.getScaleFactor();
                float prevScale = mScale;
                mScale += scale;

                if (mScale > 10f)
                    mScale = 10f;

                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
//                ScrollView layout = (ScrollView) findViewById(R.id.scrollView);
                LinearLayout layout1=(LinearLayout)findViewById(R.id.linearMatrix);
                layout1.startAnimation(scaleAnimation);
                return true;
            }
        });



    }
//to zoom the scrollView when touched
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }

}