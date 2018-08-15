package com.movinfo.movinfo.ui.movies.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movinfo.movinfo.R;
import com.movinfo.movinfo.data.network.models.MovieResponse;
import com.movinfo.movinfo.ui.movies.presenter.MoviesListPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 *
 */

public class MoviesListFragment extends Fragment implements MoviesListMvpView {
    private TextView moviesTextView;

    @Inject
    MoviesListPresenter<MoviesListMvpView> mMoviesListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ((MoviesListActivity) requireActivity()).getActivityComponent().inject(this);
        mMoviesListPresenter.onAttach(this);

        // Bind UI
        View v =  inflater.inflate(R.layout.movies_list_fragment, container, false);
        moviesTextView = v.findViewById(R.id.moviesListTextView);

        // Fetch movies
        mMoviesListPresenter.onFetchMoviesList();

        return v;
    }

    @Override
    public void displayMovies(@NonNull List<MovieResponse> movies) {
        moviesTextView.setText(movies.toString());
    }
}
