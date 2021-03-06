package com.movinfo.movinfo.ui.movies.list.presenter;

import com.movinfo.movinfo.data.network.models.MoviesResponse;
import com.movinfo.movinfo.ui.movies.list.view.MoviesListMvpView;

import retrofit2.Response;

/**
 *
 */

public interface MoviesListMvpPresenter<MoviesListView extends MoviesListMvpView> {

    void onAttach(MoviesListView mvpView);

    void refetchMovies();

    Response<MoviesResponse> getPopularMovies();

    void onSettingsClick();

    void onFinishedLoadingMovies(Response<MoviesResponse> moviesResponse);

    void onFetchMovies();

    Response<MoviesResponse> getTopRatedMovies();
}
