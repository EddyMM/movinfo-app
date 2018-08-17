package com.movinfo.movinfo.ui.movies.view;

import com.movinfo.movinfo.data.network.models.Movie;

import java.util.List;

/**
 *
 */

public interface MoviesListMvpView {
    void displayMovies(List<Movie> movies);

    void showProgressBar();

    void hideProgressBar();

    void openSettings();
}
