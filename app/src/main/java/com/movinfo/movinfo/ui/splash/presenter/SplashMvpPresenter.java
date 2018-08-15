package com.movinfo.movinfo.ui.splash.presenter;

import com.movinfo.movinfo.ui.splash.view.SplashMvpView;

/**
 *
 */

public interface SplashMvpPresenter<SplashView extends SplashMvpView> {

    void onSplashStartClick();

    void onAttach(SplashView mvpView);
}
