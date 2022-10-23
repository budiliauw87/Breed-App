package com.liaudev.catbreedsapp;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

/**
 * Created by Budiliauw87 on 2022-10-22.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class App extends Application {
    private static App mInstance;
    private SharedPreferences sharedPref;
    private String themeMode;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        try {
            final String SHARED_PREF_NAME = "breed_settings";
            MasterKey masterKey = new MasterKey.Builder(this, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            sharedPref = EncryptedSharedPreferences.create(
                    this,
                    SHARED_PREF_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            this.setThemeMode(sharedPref.getString("theme_mode", "light"));
            this.applyTheme();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyTheme() {
        int appDelegate = this.getThemeMode().equals("light")?AppCompatDelegate.MODE_NIGHT_NO:AppCompatDelegate.MODE_NIGHT_YES;
        AppCompatDelegate.setDefaultNightMode(appDelegate);
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public String getThemeMode() {
        return themeMode;
    }

    public void setThemeMode(String themeMode) {
        this.themeMode = themeMode;
    }

    public void saveThemeMode(String mode) {
        this.setThemeMode(mode);
        sharedPref.edit().putString("theme_mode", mode).apply();
        this.applyTheme();
    }
}
