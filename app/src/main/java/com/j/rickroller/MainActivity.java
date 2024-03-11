package com.j.rickroller;

import static com.j.rickroller.Config.actionBar;
import static com.j.rickroller.Config.getThemejOS;
import static com.j.rickroller.Config.getThemejOSValue;
import static com.j.rickroller.Config.get_URL;
import static com.j.rickroller.Config.get_Video;
import static com.j.rickroller.Config.relaunch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    boolean ShowVideoMenu;

    public void rickroll() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //set rickroll url
        sendIntent.putExtra(Intent.EXTRA_TEXT, get_URL(getApplicationContext()));


        sendIntent.setType("text/plain");

        // Show the Sharesheet
        startActivity(Intent.createChooser(sendIntent, null));
    }

    void videoConfig() {
        Button video = findViewById(R.id.button2);
        ShowVideoMenu = get_Video(this);
        Log.i("CONFIG", "video = " + ShowVideoMenu);
        if (ShowVideoMenu) {
            video.setVisibility(View.VISIBLE);
        } else {
            video.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getThemejOS(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        actionBar(R.string.app_name, true, this);

        Button rickroll = findViewById(R.id.button);
        Button video = findViewById(R.id.button2);
        
        videoConfig();

        rickroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rickroll();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video_menu = new Intent("com.j.rickroller.VIDEO");
                startActivity(video_menu);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        relaunch(this);
    }


}
