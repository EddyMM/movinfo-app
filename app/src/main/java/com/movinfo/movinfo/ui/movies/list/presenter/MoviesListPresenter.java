package com.movinfo.movinfo.ui.movies.list.presenter;

import com.movinfo.movinfo.data.DataManager;
import com.movinfo.movinfo.data.network.models.Movie;
import com.movinfo.movinfo.data.network.models.MoviesResponse;
import com.movinfo.movinfo.ui.base.BasePresenter;
import com.movinfo.movinfo.ui.movies.list.view.MoviesListMvpView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Handle presentation logic of movies list view
 */

public class MoviesListPresenter<MoviesListView extends MoviesListMvpView>
        extends BasePresenter implements MoviesListMvpPresenter<MoviesListView> {

    private MoviesListView mMoviesListView;

    private int mNextPage = 1;

    @Inject
    public MoviesListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void moveToNextPage() {
        mNextPage++;
    }

    @Override
    public void onAttach(MoviesListView mvpView) {
        mMoviesListView = mvpView;
    }

    @Override
    public void refetchMovies() {
        mNextPage = 1;
        mMoviesListView.resetAdapter();
    }

    public Response<MoviesResponse> getPopularMovies() {
        return getDataManager().getPopularMovies(mNextPage);
    }

    @Override
    public void onSettingsClick() {
        mMoviesListView.openSettings();
    }

    @Override
    public void onFinishedLoadingMovies(Response<MoviesResponse> moviesResponse) {
        if (moviesResponse!= null && moviesResponse.isSuccessful()) {
            Timber.d(moviesResponse.toString());

            mMoviesListView.setIsLoadingMovies(false);

            MoviesResponse popularMoviesResponse = moviesResponse.body();
            if (popularMoviesResponse != null) {
                mMoviesListView.hideProgressBar();
                List<Movie> movies = popularMoviesResponse.getResults();
                mMoviesListView.displayMovies(movies);
            } else {
                Timber.e("No movies were fetched");
            }
        } else {
            mMoviesListView.hideProgressBar();

            mMoviesListView.setIsLoadingMovies(false);
            Timber.e("Error fetching popular movies");
        }
    }

    @Override
    public void onFetchMovies() {
        mMoviesListView.loadMovies();
    }

    @Override
    public Response<MoviesResponse> getTopRatedMovies() {
        return getDataManager().getTopRatedMovies(mNextPage);
    }
}
