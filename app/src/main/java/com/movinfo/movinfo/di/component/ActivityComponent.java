package com.movinfo.movinfo.di.component;

import com.movinfo.movinfo.di.PerActivity;
import com.movinfo.movinfo.di.module.ActivityModule;
import com.movinfo.movinfo.ui.movies.view.MoviesListFragment;
import com.movinfo.movinfo.ui.splash.view.SplashFragment;

import dagger.Component;

/**
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashFragment splashFragment);

    void inject(MoviesListFragment moviesListFragment);

}
