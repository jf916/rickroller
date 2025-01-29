package com.j.rickroller;

import static com.j.rickroller.Config.get_Online_Mode;
import static com.j.rickroller.Config.get_URL;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import io.github.dot166.jLib.app.jActivity;
import io.github.dot166.jLib.utils.NetUtils;

public class MainActivity extends jActivity {
    boolean OnlineMode;
    VideoView video;
    MediaController mediaControls;
    int stopPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setSupportActionBar(findViewById(R.id.actionbar));

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
        if (OnlineMode && NetUtils.isNetworkAvailable(this)) {
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
        Log.d("Video", "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options:
                // User chooses the "Settings" item. Show the app settings UI.
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.share:
            // User chooses the "Share" action. Share the video

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                //set rickroll url
                sendIntent.putExtra(Intent.EXTRA_TEXT, get_URL(this));

                sendIntent.setType("text/plain");

                // Show the Sharesheet
                startActivity(Intent.createChooser(sendIntent, null));
            return true;

            default:
                // The user's action isn't recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
