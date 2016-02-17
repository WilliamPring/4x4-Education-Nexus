package com.example.administrator.focalpoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class trueOrFalse extends Activity {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_or_false);
        View swipe = findViewById(R.id.swipe_area);
        swipe.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){
            @Override
            public void onSwipeRight(){
                Toast.makeText(getApplicationContext(), "Swiped Right!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSwipeLeft(){
                Toast.makeText(getApplicationContext(), "Swiped Left!", Toast.LENGTH_SHORT).show();
            }

        });
    }



}
