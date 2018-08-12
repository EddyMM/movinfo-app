package com.movinfo.movinfo.ui.splash;

import android.view.View;

/**
 *
 */

public interface SplashMvpPresenter<SplashView extends SplashMvpView> {

    void onSplashStartClick();

    void onAttach(SplashView mvpView);

    void onDetach();
}
