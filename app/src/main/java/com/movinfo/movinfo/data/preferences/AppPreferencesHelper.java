package com.movinfo.movinfo.data.preferences;

import static com.movinfo.movinfo.utils.Constants.SPLASH_SCREEN_SEEN;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Helper class for storing and retrieving info from the shared preferences
 */

public class AppPreferencesHelper implements PreferencesHelper {

    SharedPreferences mSharedPreferences;

    @Inject
    public AppPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public void setSplashScreenSeen() {
        mSharedPreferences
                .edit()
                .putBoolean(SPLASH_SCREEN_SEEN, true)
                .apply();
    }

    @Override
    public boolean wasSplashScreenSeen() {
        return mSharedPreferences.getBoolean(SPLASH_SCREEN_SEEN, false);
    }
}
