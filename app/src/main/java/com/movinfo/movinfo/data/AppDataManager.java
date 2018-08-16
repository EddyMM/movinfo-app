package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.network.MovieDbApi;
import com.movinfo.movinfo.data.network.MovieDbService;
import com.movinfo.movinfo.data.network.models.PopularMoviesResponse;
import com.movinfo.movinfo.data.preferences.PreferencesHelper;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

/**
 *
 */

public class AppDataManager implements DataManager {

    private PreferencesHelper mAppPreferencesHelper;

    @Inject
    AppDataManager(PreferencesHelper appPreferencesHelper) {
        mAppPreferencesHelper = appPreferencesHelper;
    }

    @Override
    public void setSplashScreenSeenByUser() {
        mAppPreferencesHelper.setSplashScreenSeen();
    }

    @Override
    public boolean wasSplashScreenSeen() {
        return mAppPreferencesHelper.wasSplashScreenSeen();
    }

    @Override
    public void getPopularMovies(Callback<PopularMoviesResponse> popularMoviesCallback) {
        MovieDbService movieDbService = MovieDbApi.getInstance();

        Call<PopularMoviesResponse> popularMoviesCall = movieDbService.getPopularMovies();
        popularMoviesCall.enqueue(popularMoviesCallback);
    }
}
