package com.movinfo.movinfo.ui.splash;

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
