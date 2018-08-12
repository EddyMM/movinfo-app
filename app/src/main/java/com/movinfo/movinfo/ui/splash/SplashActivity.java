package com.movinfo.movinfo.ui.splash;

import android.support.v4.app.Fragment;

import com.movinfo.movinfo.ui.base.SingleFragmentActivity;

public class SplashActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SplashFragment();
    }
}
