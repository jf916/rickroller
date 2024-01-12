package com.j.rickroller;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class VideoActivity extends Activity {
    VideoView video;
    int stopPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

        video = findViewById(R.id.video);
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rickroll));
        video.start();
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
        Log.d("Video", "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}
