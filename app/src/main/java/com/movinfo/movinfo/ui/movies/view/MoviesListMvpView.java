package com.movinfo.movinfo.ui.movies.view;

import com.movinfo.movinfo.data.network.models.MovieResponse;

import java.util.List;

/**
 *
 */

public interface MoviesListMvpView {
    void displayMovies(List<MovieResponse> movies);
}
