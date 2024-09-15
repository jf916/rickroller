package com.j.rickroller;

import jOS.Core.jConfigActivity;

/**
 * Settings activity for Launcher. Currently implements the following setting: Allow rotation
 */
public class SettingsActivity extends jConfigActivity {
    @Override
    public int preferenceFragmentValue() {
        return R.string.rickroller_settings_fragment_name;
    }
    public static class RickrollerSettingsFragment extends LauncherSettingsFragment {
        @Override
        public int preferenceXML() {
            return R.xml.rickroller_preferences;
        }
    }
}
