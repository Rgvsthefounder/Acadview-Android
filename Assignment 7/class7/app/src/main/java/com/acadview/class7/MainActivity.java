package com.acadview.class7;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name,cls,age;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference
        name = findViewById(R.id.editText);
        cls =findViewById(R.id.editText2);
        age = findViewById(R.id.editText3);
        btn = findViewById(R.id.button);

        //Listen button click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if Logic for empty submissions by user

                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Require Field");
                    return;
                }

                if(TextUtils.isEmpty(cls.getText().toString())){
                    name.setError("Require Field");
                    return;
                }

                if(TextUtils.isEmpty(age.getText().toString())){
                    name.setError("Require Field");
                    return;
                }

                String userName,userClass,userAge = null ;
                userName = name.getText().toString();
                userClass = cls.getText().toString();
                userAge = age.getText().toString();
                //for entering data into shared preference
                //shared preference start here
                SharedPreferences sp  = getSharedPreferences("details",MODE_PRIVATE);
                SharedPreferences.Editor editor =sp.edit();
                //add values
                editor.putString("name",userName);
                editor.putString("cls",userClass);
                editor.putString("age",userAge);
                //class editor
                editor.commit();
                //getfialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Submit Confirmation");
                builder.setMessage("Are You Sure Youwant To Submit ");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this,output.class);
                        startActivity(intent);
                    }
                });

               builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finish();
                   }
               }); builder.show();
            }
        });
    }
}
