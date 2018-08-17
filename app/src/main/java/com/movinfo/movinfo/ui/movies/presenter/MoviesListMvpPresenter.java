package com.movinfo.movinfo.ui.movies.presenter;

import com.movinfo.movinfo.ui.movies.view.MoviesListMvpView;

/**
 *
 */

public interface MoviesListMvpPresenter<MoviesListView extends MoviesListMvpView> {

    void onAttach(MoviesListView mvpView);

    void onFetchMoviesList();

    void onSettingsClick();
}
