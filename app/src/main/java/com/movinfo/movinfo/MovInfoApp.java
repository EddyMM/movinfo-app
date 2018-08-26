package com.movinfo.movinfo;

import android.app.Application;

import com.movinfo.movinfo.di.component.ApplicationComponent;
import com.movinfo.movinfo.di.component.DaggerApplicationComponent;
import com.movinfo.movinfo.di.module.ApplicationModule;

import timber.log.Timber;


/**
 *
 */

public class MovInfoApp extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the timber for logging
        initTimber();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

    /**
     * Initialize timber logging by specifying a tree
     */
    private void initTimber() {
        if (BuildConfig.DEBUG) {
            // Plant timber tree for logging
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
