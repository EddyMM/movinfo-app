package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.preferences.AppPreferencesHelper;
import com.movinfo.movinfo.data.preferences.PreferencesHelper;

import javax.inject.Inject;

/**
 *
 */

public class AppDataManager implements DataManager {

    private PreferencesHelper mAppPreferencesHelper;

    @Inject
    public AppDataManager(PreferencesHelper appPreferencesHelper) {
        mAppPreferencesHelper = appPreferencesHelper;
    }

    @Override
    public void setSplashScreenSeenByUser() {
        mAppPreferencesHelper.setSplashScreenSeen();
    }

    @Override
    public boolean wasSplashScreenSeen() {
        return mAppPreferencesHelper.wasSplashScreenSeen();
    }
}
