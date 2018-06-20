package com.acadview.class7;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class output extends AppCompatActivity {

    TextView name,cls,age;
    String userName,userClass,userAge = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        name= findViewById(R.id.tv1);
        cls = findViewById(R.id.tv2);
        age = findViewById(R.id.tv3);

        SharedPreferences sp  = getSharedPreferences("details",MODE_PRIVATE);

        //Create editor for shared preference
        userName = sp.getString("name",userName);
        userClass = sp.getString("cls",userClass);
        userAge = sp.getString("age",userAge);
      //set the values to textview
        name.setText(userName);
        cls.setText(userClass);
        age.setText(userAge);
    }
}
