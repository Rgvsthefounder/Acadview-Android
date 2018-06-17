package com.acadview.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class mainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  Button btn = findViewById(R.id.loginbutton);
  btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          Intent intent = new Intent(mainActivity.this,RegistrationForm.class);
          startActivity(intent);

      }
  });




    }
}
