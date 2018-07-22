package com.assembled.ChatGroups;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.assembled.firebase.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler;

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(Splash.this, MainActivity.class));

                finish();
            }
        }, 3000);
    }

}
