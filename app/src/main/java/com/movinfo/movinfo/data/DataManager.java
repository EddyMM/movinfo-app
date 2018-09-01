package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.network.models.MoviesResponse;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */

public interface DataManager {

    void setSplashScreenSeenByUser();

    boolean wasSplashScreenSeen();

    Response<MoviesResponse> getPopularMovies(int page);

    void getTopRatedMovies(Callback<MoviesResponse> topRatedMoviesCallback, int page);

}
