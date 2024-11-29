package com.j.rickroller;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.Objects;

public class Config {
    public static final String KEY_SHOW_VID = "pref_video";
    public static final String KEY_ONLINE = "pref_online";
    public static final String KEY_CUSTOM_URL_PREF = "pref_custom_url";
    public static final String KEY_CUSTOM_URL = "pref_url";
    public static final String DEFAULT_URL = "https://dot166.github.io/coolvideo.mp4";

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

    private static boolean get_custom_url_pref(Context context) {
        SharedPreferences prefs;
        boolean CUSTOM_URL_PREF;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        CUSTOM_URL_PREF = prefs.getBoolean(KEY_CUSTOM_URL_PREF, false);
        return CUSTOM_URL_PREF;
    }

    private static String get_custom_url(Context context) {
        SharedPreferences prefs;
        String CUSTOM_URL;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        CUSTOM_URL = prefs.getString(KEY_CUSTOM_URL, null);
        if (Objects.equals(CUSTOM_URL, "")) {
            return null;
        } else {
            return CUSTOM_URL;
        }
    }

    public static String get_URL(Context context) {
        if (get_custom_url_pref(context) && get_custom_url(context) != null) {
            return get_custom_url(context);
        } else {
            return DEFAULT_URL;
        }
    }

}
