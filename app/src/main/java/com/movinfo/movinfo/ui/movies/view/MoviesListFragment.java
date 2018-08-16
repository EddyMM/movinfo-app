package com.movinfo.movinfo.ui.movies.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.movinfo.movinfo.R;
import com.movinfo.movinfo.data.network.models.Movie;
import com.movinfo.movinfo.ui.movies.presenter.MoviesListAdapter;
import com.movinfo.movinfo.ui.movies.presenter.MoviesListPresenter;
import com.movinfo.movinfo.utils.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 *
 */

public class MoviesListFragment extends Fragment implements MoviesListMvpView {
    private RecyclerView mMoviesRecyclerView;
    private ProgressBar mMoviesListProgressBar;

    @Inject
    MoviesListPresenter<MoviesListMvpView> mMoviesListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ((MoviesListActivity) requireActivity()).getActivityComponent().inject(this);
        mMoviesListPresenter.onAttach(this);

        // Bind UI
        View v = inflater.inflate(R.layout.movies_list_fragment, container, false);
        mMoviesRecyclerView = v.findViewById(R.id.moviesListRecyclerView);
        mMoviesRecyclerView.setLayoutManager(
                new GridLayoutManager(this.getContext(), Constants.MOVIES_LIST_NO_OF_COLUMNS));

        mMoviesListProgressBar = v.findViewById(R.id.moviesListProgressBar);

        // Fetch movies
        mMoviesListPresenter.onFetchMoviesList();

        return v;
    }

    @Override
    public void displayMovies(@NonNull List<Movie> movies) {
        mMoviesRecyclerView.setAdapter(new MoviesListAdapter(this.getContext(), movies));
    }

    @Override
    public void showProgressBar() {
        mMoviesListProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mMoviesListProgressBar.setVisibility(View.INVISIBLE);
    }
}
