package com.acadview.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class mainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast topToast=null;
        topToast = Toast.makeText(this,"This is my toast",Toast.LENGTH_LONG);

        topToast.setGravity(Gravity.TOP,0,0);

        topToast.show();


    }
}
