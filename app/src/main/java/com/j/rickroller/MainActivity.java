package com.j.rickroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public void rickroll() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //set rickroll url
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");


        sendIntent.setType("text/plain");

        // Show the Sharesheet
        startActivity(Intent.createChooser(sendIntent, null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button rickroll = findViewById(R.id.button);

        rickroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rickroll();
            }
        });
    }


}
