package com.movinfo.movinfo.di.module;

import static com.movinfo.movinfo.utils.Constants.SHARED_PREFERENCES_NAME;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.movinfo.movinfo.MovInfoApp;
import com.movinfo.movinfo.data.AppDataManager;
import com.movinfo.movinfo.data.DataManager;
import com.movinfo.movinfo.data.preferences.AppPreferencesHelper;
import com.movinfo.movinfo.data.preferences.PreferencesHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    MovInfoApp provideApplication() {
        return (MovInfoApp) mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return mApplication.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
