package com.j.rickroller;

import static com.j.rickroller.settings.KEY_SHOW_VID_SHARE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.preference.PreferenceManager;

public class VideoMenuActivity extends Activity {
    SharedPreferences prefs;
    boolean ShowVideoShare;

    public void rickroll() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //set rickroll url
        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rickroll));


        sendIntent.setType("video/mp4");

        // Show the Sharesheet
        startActivity(Intent.createChooser(sendIntent, null));
    }

    void videoConfig() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ShowVideoShare = prefs.getBoolean(KEY_SHOW_VID_SHARE, false);
        Button rickroll = findViewById(R.id.button22);
        Log.i("CONFIG", "video_share = " + ShowVideoShare);
        if (ShowVideoShare) {
            rickroll.setVisibility(View.VISIBLE);
        } else {
            rickroll.setVisibility(View.INVISIBLE);
            Intent SkiptoVid = new Intent("com.j.rickroller.SHOWVID");
            startActivity(SkiptoVid);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_menu_activity);

        videoConfig();

        Button rickroll = findViewById(R.id.button22);
        Button video = findViewById(R.id.button21);

        rickroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rickroll();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video_menu = new Intent("com.j.rickroller.SHOWVID");
                startActivity(video_menu);
            }
        });
    }
}