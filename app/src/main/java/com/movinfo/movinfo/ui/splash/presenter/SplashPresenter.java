package com.movinfo.movinfo.ui.splash.presenter;

import android.support.v7.app.AppCompatActivity;

import com.movinfo.movinfo.data.DataManager;
import com.movinfo.movinfo.data.preferences.AppPreferencesHelper;
import com.movinfo.movinfo.ui.base.BasePresenter;
import com.movinfo.movinfo.ui.splash.view.SplashMvpView;

import javax.inject.Inject;

/**
 *
 */

public class SplashPresenter<SplashView extends SplashMvpView> extends BasePresenter
        implements SplashMvpPresenter<SplashView> {

    private SplashView mSplashView;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSplashStartClick() {
        getDataManager().setSplashScreenSeenByUser();

        mSplashView.openMoviesList();
        mSplashView.close();
    }

    @Override
    public void onAttach(SplashView mvpView) {
        mSplashView = mvpView;
        if (getDataManager().wasSplashScreenSeen()) {
            mSplashView.openMoviesList();
        }
    }

    @Override
    public void onDetach() {

    }
}
