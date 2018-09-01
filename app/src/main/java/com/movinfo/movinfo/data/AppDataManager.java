package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.network.MovieDbApi;
import com.movinfo.movinfo.data.network.MovieDbService;
import com.movinfo.movinfo.data.network.models.MoviesResponse;
import com.movinfo.movinfo.data.preferences.PreferencesHelper;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public Response<MoviesResponse> getPopularMovies(int page) {
        MovieDbService movieDbService = MovieDbApi.getInstance(page);

        Call<MoviesResponse> popularMoviesCall = movieDbService.getPopularMovies();
        try {
            return popularMoviesCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        popularMoviesCall.enqueue(popularMoviesCallback);
    }

    @Override
    public void getTopRatedMovies(Callback<MoviesResponse> topRatedMoviesCallback, int page) {
        MovieDbService movieDbService = MovieDbApi.getInstance(page);

        Call<MoviesResponse> topRatedMoviesCall = movieDbService.getTopRatedMovies();
        topRatedMoviesCall.enqueue(topRatedMoviesCallback);
    }
}
