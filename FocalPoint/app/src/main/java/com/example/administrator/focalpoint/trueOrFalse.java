package com.example.administrator.focalpoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class trueOrFalse extends Activity {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_or_false);
        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
                        return false;
                    }
                    // right to left swipe
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        onLeftSwipe();
                    }
                    // left to right swipe
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        onRightSwipe();
                    }
                } catch (Exception e) {

                }
                return false;
            }
            private void onLeftSwipe(){
                Toast.makeText(getApplicationContext(), "Swiped Left!", Toast.LENGTH_SHORT).show();
            }
            private void onRightSwipe(){
                Toast.makeText(getApplicationContext(), "Swiped Right!", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
