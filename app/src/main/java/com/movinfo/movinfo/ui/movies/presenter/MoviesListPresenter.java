package com.movinfo.movinfo.ui.movies.presenter;

import android.support.annotation.NonNull;

import com.movinfo.movinfo.data.DataManager;
import com.movinfo.movinfo.data.network.models.Movie;
import com.movinfo.movinfo.data.network.models.PopularMoviesResponse;
import com.movinfo.movinfo.ui.base.BasePresenter;
import com.movinfo.movinfo.ui.movies.view.MoviesListMvpView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Handle presentation logic of movies list view
 */

public class MoviesListPresenter<MoviesListView extends MoviesListMvpView>
        extends BasePresenter implements MoviesListMvpPresenter<MoviesListView> {

    private MoviesListView mMoviesListView;

    @Inject
    public MoviesListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(MoviesListView mvpView) {
        mMoviesListView = mvpView;
    }

    @Override
    public void onFetchMoviesList(@NonNull String sortOrder) {
        mMoviesListView.showProgressBar();
        getDataManager().getPopularMovies(
                new Callback<PopularMoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<PopularMoviesResponse> call,
                            @NonNull Response<PopularMoviesResponse> response) {
                        Timber.d(response.toString());

                        PopularMoviesResponse popularMoviesResponse = response.body();
                        if (popularMoviesResponse != null) {
                            mMoviesListView.hideProgressBar();
                            List<Movie> movies = popularMoviesResponse.getResults();
                            mMoviesListView.displayMovies(movies, sortOrder);
                        } else {
                            Timber.e("No movies were fetched");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PopularMoviesResponse> call,
                            @NonNull Throwable t) {
                        mMoviesListView.hideProgressBar();
                        Timber.e("Error fetching popular movies: " + t.getMessage());
                        t.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void onSettingsClick() {
        mMoviesListView.openSettings();
    }
}
