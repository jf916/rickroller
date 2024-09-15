package com.j.rickroller;

import static com.j.rickroller.Config.get_URL;
import static com.j.rickroller.Config.get_Video;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jOS.Core.jActivity;

public class MainActivity extends jActivity {
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
        configure(R.layout.main_activity, true);
        super.onCreate(savedInstanceState);

        Button rickroll = findViewById(R.id.button);
        Button video = findViewById(R.id.button2);
        Button about = findViewById(R.id.button3);
        
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

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about_menu = new Intent(v.getContext(), About.class);
                startActivity(about_menu);
            }
        });
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
                startActivity(new Intent("com.j.rickroller.CONFIG"));
                return true;

            //case R.id.action_favorite:
            //// User chooses the "Favorite" action. Mark the current item as a
            //// favorite.
            //return true;

            default:
                // The user's action isn't recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
