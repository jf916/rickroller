package com.j.rickroller;

import static com.j.rickroller.Config.get_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_bottom_sheet_content, container, false);
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                //set rickroll url
                sendIntent.putExtra(Intent.EXTRA_TEXT, get_URL(v.getContext()));

                sendIntent.setType("text/plain");

                // Show the Sharesheet
                startActivity(Intent.createChooser(sendIntent, null));
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                //set rickroll uri
                Uri uriToImage = Uri.parse("android.resource://" + v.getContext().getPackageName() + "/" + R.raw.rickroll);
                sendIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);

                sendIntent.setType("video/mp4");

                // Show the Sharesheet
                startActivity(Intent.createChooser(sendIntent, null));
            }
        });
        return view;
    }

    public static final String TAG = "RickrollerBottomSheet";
}