package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.network.models.PopularMoviesResponse;

import retrofit2.Callback;

/**
 *
 */

public interface DataManager {

    void setSplashScreenSeenByUser();

    boolean wasSplashScreenSeen();

    void getPopularMovies(Callback<PopularMoviesResponse> popularMoviesCallback);

}
