package com.j.rickroller;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.preference.Preference;

import jOS.Core.jConfigActivity;

/**
 * Settings activity for Launcher. Currently implements the following setting: Allow rotation
 */
public class SettingsActivity extends jConfigActivity {
    @Override
    public jLIBSettingsFragment preferenceFragment() {
        return new RickrollerSettingsFragment();
    }
    public static class RickrollerSettingsFragment extends jLIBSettingsFragment {
        @Override
        public int preferenceXML() {
            return R.xml.rickroller_preferences;
        }
        @Override
        protected boolean extraPrefs(Preference preference) {
            switch (preference.getKey()) {
                case "pref_about":
                    preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(@NonNull Preference preference) {
                            startActivity(new Intent(preference.getContext(), About.class));
                            return true;
                        }
                    });
                    return true;
                default:
                    return true;
            }
        }
    }
}
