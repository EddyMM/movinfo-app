package com.movinfo.movinfo.di.component;

import com.movinfo.movinfo.MovInfoApp;
import com.movinfo.movinfo.data.DataManager;
import com.movinfo.movinfo.data.preferences.PreferencesHelper;
import com.movinfo.movinfo.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MovInfoApp app);

    void inject(PreferencesHelper preferencesHelper);

    DataManager getDataManager();
}
