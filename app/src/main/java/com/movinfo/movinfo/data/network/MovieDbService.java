package com.movinfo.movinfo.data.network;

import com.movinfo.movinfo.data.network.models.PopularMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 */

public interface MovieDbService {
    @GET("movie/popular")
    Call<PopularMoviesResponse> getPopularMovies();
}
