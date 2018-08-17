package com.movinfo.movinfo.ui.movies.view;

import android.support.annotation.NonNull;

import com.movinfo.movinfo.data.network.models.Movie;

import java.util.List;

/**
 *
 */

public interface MoviesListMvpView {
    void displayMovies(@NonNull List<Movie> movies);

    void showProgressBar();

    void hideProgressBar();

    void openSettings();
}
