package com.movinfo.movinfo.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.movinfo.movinfo.di.PerActivity;
import com.movinfo.movinfo.ui.splash.presenter.SplashMvpPresenter;
import com.movinfo.movinfo.ui.splash.presenter.SplashPresenter;
import com.movinfo.movinfo.ui.splash.view.SplashMvpView;


import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> splashPresenter) {
        return splashPresenter;
    }

}
