package com.zakariaboukernafa.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent svc = new Intent(this, MusicManager.class);
        startService(svc);


        Button start = findViewById(R.id.Start_game);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                Intent i = new Intent(MainActivity.this, Game.class);
                startActivity(i);
            }
        });
        Button Ai_Game = findViewById(R.id.Start_AI);
        Ai_Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, AI_Bot.class);

                startActivity(i);
            }
        });
        Button about = findViewById(R.id.About_game);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, MusicManager.class));

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        stopService(new Intent(this, MusicManager.class));

        super.onPause();

    }

    @Override
    protected void onResume() {
        Intent svc = new Intent(this, MusicManager.class);
        startService(svc);
        super.onResume();
    }
}