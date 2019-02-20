package com.zakariaboukernafa.tictactoe;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class MusicManager extends Service {


    private static final String TAG = null;
    MediaPlayer player;

    public IBinder onBind(Intent arg0) {

        return null;

    }


    @Override
    public void onCreate() {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.background_music);
        player.start();
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);


    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_STICKY;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    @Override
    public void onDestroy() {
        player.stop();

        player.release();
    }

    @Override
    public void onLowMemory() {
    }
}