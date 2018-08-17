package com.movinfo.movinfo.ui.movies.presenter;

import android.support.annotation.NonNull;

import com.movinfo.movinfo.ui.movies.view.MoviesListMvpView;

/**
 *
 */

public interface MoviesListMvpPresenter<MoviesListView extends MoviesListMvpView> {

    void onAttach(MoviesListView mvpView);

    void onFetchMoviesList(@NonNull String sortOrder);

    void onSettingsClick();
}
