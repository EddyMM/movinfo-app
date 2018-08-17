package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.network.models.MoviesResponse;

import retrofit2.Callback;

/**
 *
 */

public interface DataManager {

    void setSplashScreenSeenByUser();

    boolean wasSplashScreenSeen();

    void getPopularMovies(Callback<MoviesResponse> popularMoviesCallback);

    void getTopRatedMovies(Callback<MoviesResponse> topRatedMoviesCallback);

}
