package com.acadview.otpregistration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SMS = 101;
    public TextView nameTV,phoneTV;
    public EditText nameEditText,phoneEditText;
    Button otpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTV = findViewById(R.id.nameTV);
        phoneTV = findViewById(R.id.phoneTV);
        phoneEditText = findViewById(R.id.phoneEditText);
        nameEditText = findViewById(R.id.nameEditText);
        otpBtn = findViewById(R.id.otpBtn);

         String[] permission = {
                 Manifest.permission.SEND_SMS
};

            //1 check if sms permission is granted
            if(ActivityCompat.checkSelfPermission(this,permission[0]) != PackageManager.PERMISSION_GRANTED){

            //if  permission is not granted
            ActivityCompat.requestPermissions(MainActivity.this, permission, REQUEST_CODE_SMS);
        }

        otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if Logic for empty submissions by user

                if(TextUtils.isEmpty(nameEditText.getText().toString())){
                    nameEditText.setError("Require Field");
                    return;
                }

                if(TextUtils.isEmpty(phoneEditText.getText().toString())){
                    phoneEditText.setError("Require Field");
                    return;
                }

                String PhoneNo = phoneEditText.getText().toString().trim();

                Toast.makeText(MainActivity.this,"Sending",Toast.LENGTH_LONG).show();
                SmsManager sms = SmsManager.getDefault();
                Random r = new Random();
                int i1 = r.nextInt(9999 - 1111) + 1111;
                String messageNumber= "Hey !! Your OTP Is " +i1+ "Donot Disclose To anybody";

                // for sending otp message
                sms.sendTextMessage(PhoneNo,null,messageNumber,null,null);

                Intent intent=new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtra("OTP",i1+"");
                startActivity(intent);



            }
        });
    }
}
