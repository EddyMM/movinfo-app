package com.movinfo.movinfo.ui.movies.list.presenter;

import com.movinfo.movinfo.ui.movies.list.view.MoviesListMvpView;

/**
 *
 */

public interface MoviesListMvpPresenter<MoviesListView extends MoviesListMvpView> {

    void onAttach(MoviesListView mvpView);

    void refetchMovies();

    void onFetchPopularMovies();

    void onFetchTopRatedMovies();

    void onSettingsClick();
}
