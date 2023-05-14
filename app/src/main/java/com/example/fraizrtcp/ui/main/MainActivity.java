package com.example.fraizrtcp.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.example.fraizrtcp.R;
import com.example.fraizrtcp.ui.user.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.splash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        motionLayout.findViewById(R.id.motionmain);
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if (currentId == R.id.end) {
                    Intent in = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(in);
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }
}