package com.movinfo.movinfo.ui.splash.presenter;

import com.movinfo.movinfo.ui.splash.view.SplashMvpView;

/**
 *
 */

public class SplashPresenter<SplashView extends SplashMvpView>
        implements SplashMvpPresenter<SplashView> {

    private SplashView mSplashView;

    @Override
    public void onSplashStartClick() {
        mSplashView.openMoviesList();
    }

    @Override
    public void onAttach(SplashView mvpView) {
        mSplashView = mvpView;
    }

    @Override
    public void onDetach() {

    }
}
