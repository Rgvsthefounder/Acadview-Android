package com.acadview.otpregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    public EditText otpEditText;
    Button confirmBtn;
    String otpNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        otpEditText = findViewById(R.id.otpEditText);
        confirmBtn = findViewById(R.id.confirmBtn);

        otpNumber=getIntent().getStringExtra("OTP");


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(otpEditText.getText().toString())){
                    otpEditText.setError("Require Field");
                    return;
                }

                String otp = otpEditText.getText().toString().trim();

                if(otp.equals(otpNumber)){
                    Toast.makeText(DisplayActivity.this, "OTP Confirmed,Thanx For Registering", Toast.LENGTH_SHORT).show();
                }
               else{
                    Toast.makeText(DisplayActivity.this, "Incorrect OTP,Registration Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
