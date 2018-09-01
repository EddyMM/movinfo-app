package com.movinfo.movinfo.ui.movies.list.view;

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

    boolean isInternetConnected();

    void showNoInternetConnectionMessage();

    void removeNoInternetConnectionMessage();

    void setIsLoadingMovies(boolean isLoadingMovies);

    void loadMovies();

    void resetAdapter();

    void openSettings();
}
