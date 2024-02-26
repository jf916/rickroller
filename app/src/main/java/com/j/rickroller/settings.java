package com.j.rickroller;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class settings {
    public static final String KEY_SHOW_VID = "pref_video";
    public static final String KEY_ONLINE = "pref_online";

    public static boolean get_Online_Mode(Context context) {
        SharedPreferences prefs;
        boolean OnlineMode;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        OnlineMode = prefs.getBoolean(KEY_ONLINE, false);
        return OnlineMode;
    }

    public static boolean get_Video(Context context) {
        SharedPreferences prefs;
        boolean Video;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Video = prefs.getBoolean(KEY_SHOW_VID, false);
        return Video;
    }
}
