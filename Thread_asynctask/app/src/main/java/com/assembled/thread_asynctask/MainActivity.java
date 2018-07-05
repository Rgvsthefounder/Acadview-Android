package com.assembled.thread_asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1,button2;
    ImageView imageView;
    ProgressBar progressBar;
    //thread1 = UI Thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);

        progressBar = findViewById(R.id.progressBar);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "HII I Am Toast", Toast.LENGTH_SHORT).show();

            }
        });

        //on image buton click

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // loadImage();
           new LoadIconTask().execute(R.drawable.songg);
            }
        });
    }

    class LoadIconTask extends AsyncTask<Integer,Integer,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          progressBar.setVisibility(ProgressBar.VISIBLE);

        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {

            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){}
            Bitmap tmp = BitmapFactory.decodeResource(getResources(),R.drawable.songg);
            return tmp;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            progressBar.setVisibility(ProgressBar.INVISIBLE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


    }

   // private void loadImage() {
    //   new Thread(new Runnable() {
       //     @Override
         //   public void run() {

           //     final Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.songg);
             //   MainActivity.this.runOnUiThread(new Runnable() {

               //     @Override
                 //   public void run() {
                   //     imageView.setImageBitmap(bitmap);
              //      }
            //    });
          //  }
        //}).start();
    }

