package com.j.rickroller;

import static com.j.rickroller.Config.getThemejOS;
import static com.j.rickroller.Config.get_Online_Mode;
import static com.j.rickroller.Config.get_URL;
import static com.j.rickroller.Config.relaunch;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
    boolean OnlineMode;
    VideoView video;
    MediaController mediaControls;
    int stopPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getThemejOS(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

        video = findViewById(R.id.video);
        OnlineMode = get_Online_Mode(this);
        Log.i("CONFIG", "online = " + OnlineMode);
        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(this);
            mediaControls.setAnchorView(video);
        }
        // set the media controller for video view
        video.setMediaController(mediaControls);
        if (OnlineMode) {
            video.setVideoURI(Uri.parse(get_URL(getApplicationContext())));
            video.start();
        } else {
            video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rickroll));
            video.start();
        }
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }
    @Override
    public void onPause() {
        Log.d("Video", "onPause called");
        super.onPause();
        stopPosition = video.getCurrentPosition(); //stopPosition is an int
        video.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        relaunch(this);
        Log.d("Video", "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}
