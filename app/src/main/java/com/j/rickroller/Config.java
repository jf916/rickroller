package com.j.rickroller;

import static jOS.Core.ActionBar.actionBarConfig;
import static jOS.Core.ThemeEngine.getSystemTheme;
import static jOS.Core.ThemeEngine.getThemeFromDB1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Objects;

public class Config {
    public static final String KEY_SHOW_VID = "pref_video";
    public static final String KEY_ONLINE = "pref_online";
    public static final String KEY_CUSTOM_URL_PREF = "pref_custom_url";
    public static final String KEY_CUSTOM_URL = "pref_url";
    public static final String KEY_THEME = "pref_theme";
    public static final String DEFAULT_URL = "https://dot166.github.io/coolvideo.mp4";
    static String currentTheme;

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

    public static void actionBar(int name, boolean home, AppCompatActivity context){
        actionBarConfig(name, R.mipmap.ic_launcher, home, context, "com.j.rickroller.CONFIG");
    }

    public static int getThemejOS(Context context){
        SharedPreferences prefs;
        String Theme;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Theme = prefs.getString(KEY_THEME, "jOS_System");
        currentTheme = getThemejOSValue(context);
        switch (Theme) {
            case "jOS_System":
                Log.i("Theme Engine", getThemeFromDB1(context));
                return getSystemTheme(context);
            case "Holo":
                Log.i("Theme Engine", "jOS.Core.R.style.jOS_Theme");
                return jOS.Core.R.style.jOS_Theme;
            case "M3 Dark":
                Log.i("Theme Engine", "com.google.android.material.R.style.Theme_Material3_Dark_NoActionBar");
                return com.google.android.material.R.style.Theme_Material3_Dark_NoActionBar;
            case "M3 Light":
                Log.i("Theme Engine", "com.google.android.material.R.style.Theme_Material3_Light_NoActionBar");
                return com.google.android.material.R.style.Theme_Material3_Light_NoActionBar;
        }
        prefs.edit().putString(KEY_THEME, "jOS_System").apply();
        throw new IllegalArgumentException("Unrecognised Theme");
    }

    public static String getThemejOSValue(Context context){
        SharedPreferences prefs;
        String Theme;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Theme = prefs.getString(KEY_THEME, "jOS_System");
        return Theme;
    }

    public static void relaunch(Activity context) {
        if (!Objects.equals(currentTheme, getThemejOSValue(context))) {
            Intent intent = context.getIntent();
            context.finish();
            context.startActivity(intent);
        }
    }
}
