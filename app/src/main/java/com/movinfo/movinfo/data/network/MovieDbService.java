package com.movinfo.movinfo.data.network;

import com.movinfo.movinfo.data.network.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 */

public interface MovieDbService {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies();

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies();
}
