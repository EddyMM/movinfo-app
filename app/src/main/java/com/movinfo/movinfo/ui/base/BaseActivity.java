package com.movinfo.movinfo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.movinfo.movinfo.MovInfoApp;
import com.movinfo.movinfo.di.component.ActivityComponent;
import com.movinfo.movinfo.di.component.DaggerActivityComponent;
import com.movinfo.movinfo.di.module.ActivityModule;


/**
 *
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MovInfoApp) getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

}
