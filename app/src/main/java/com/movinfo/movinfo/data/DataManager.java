package com.movinfo.movinfo.data;

import com.movinfo.movinfo.data.network.models.MoviesResponse;

import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */

public interface DataManager {

    void setSplashScreenSeenByUser();

    boolean wasSplashScreenSeen();

    Response<MoviesResponse> getPopularMovies(int page);

    Response<MoviesResponse> getTopRatedMovies(int page);

}
