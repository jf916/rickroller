package com.j.rickroller;

import static com.j.rickroller.settings.KEY_SHOW_VID;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.preference.PreferenceManager;

public class MainActivity extends Activity {
    SharedPreferences prefs;
    boolean ShowVideoMenu;

    public void rickroll() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //set rickroll url
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");


        sendIntent.setType("text/plain");

        // Show the Sharesheet
        startActivity(Intent.createChooser(sendIntent, null));
    }

    void videoConfig() {
        Button video = findViewById(R.id.button2);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ShowVideoMenu = prefs.getBoolean(KEY_SHOW_VID, false);
        Log.i("CONFIG", "video = " + ShowVideoMenu);
        if (ShowVideoMenu) {
            video.setVisibility(View.VISIBLE);
        } else {
            video.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button rickroll = findViewById(R.id.button);
        Button video = findViewById(R.id.button2);
        Button Config = findViewById(R.id.floatingActionButton);
        
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

        Config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ConfigIntent = new Intent("com.j.rickroller.CONFIG");
                startActivity(ConfigIntent);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        videoConfig();
    }


}
