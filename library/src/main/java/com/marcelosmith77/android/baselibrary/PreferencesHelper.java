package com.marcelosmith77.android.baselibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

public class PreferencesHelper {

    private SharedPreferences settings;

    public PreferencesHelper(Context context) {
        this.settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @SuppressWarnings("unchecked")
    public <T> T readValue(String key, T defaultValue) {
        Map<String, ?> map = settings.getAll();
        T value = (T) map.get(key);
        return value != null ? value : defaultValue;
    }

    public void putLong(String key, Long value) {
        SharedPreferences.Editor preferencesEditor = settings.edit();
        preferencesEditor.putLong(key, value);
        preferencesEditor.apply();
    }

    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor preferencesEditor = settings.edit();
        preferencesEditor.putBoolean(key, value);
        preferencesEditor.apply();
    }

    public void putFloat(String key, Float value) {
        SharedPreferences.Editor preferencesEditor = settings.edit();
        preferencesEditor.putFloat(key, value);
        preferencesEditor.apply();
    }

    public void putInt(String key, Integer value) {
        SharedPreferences.Editor preferencesEditor = settings.edit();
        preferencesEditor.putInt(key, value);
        preferencesEditor.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor preferencesEditor = settings.edit();
        preferencesEditor.putString(key, value);
        preferencesEditor.apply();
    }

    public void putStringSet(String key, Set<String> value) {
        SharedPreferences.Editor preferencesEditor = settings.edit();
        preferencesEditor.putStringSet(key, value);
        preferencesEditor.apply();
    }

}
