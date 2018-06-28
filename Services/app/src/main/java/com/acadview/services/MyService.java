package com.acadview.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class MyService extends Service {

    MediaPlayer mediaplayer;
    int cur; // cursor position
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       mediaplayer = MediaPlayer.create(this,R.raw.take);
       mediaplayer.setLooping(false);
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        if (intent.getStringExtra("state").equals("play")) {
            mediaplayer.start();
           // mediaplayer.setLooping(true);
        }
        else if (intent.getStringExtra("state").equals("pause")){
            cur=mediaplayer.getCurrentPosition();
            mediaplayer.pause();
        }

        else if (intent.getStringExtra("state").equals("resume")){
            mediaplayer.seekTo(cur);
            mediaplayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        mediaplayer.stop();
    }
}
