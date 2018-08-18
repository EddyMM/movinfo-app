package com.movinfo.movinfo.ui.movies.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.movinfo.movinfo.R;
import com.movinfo.movinfo.data.network.models.Movie;
import com.movinfo.movinfo.ui.movies.presenter.MoviesListAdapter;
import com.movinfo.movinfo.ui.movies.presenter.MoviesListPresenter;
import com.movinfo.movinfo.ui.movies.view.settings.SettingsActivity;
import com.movinfo.movinfo.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Fragment to host and manage movies list
 */

public class MoviesListFragment extends Fragment implements MoviesListMvpView,
        SharedPreferences.OnSharedPreferenceChangeListener {
    private ProgressBar mMoviesListProgressBar;
    private MoviesListAdapter mMoviesListAdapter;
    private GridLayoutManager mMoviesListGridLayout;

    private boolean isLoading;
    private String mSortCriteria;

    @Inject
    MoviesListPresenter<MoviesListMvpView> mMoviesListPresenter;

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(requireContext());

        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        // Ensure menu is displayed
        setHasOptionsMenu(true);

        ((MoviesListActivity) requireActivity()).getActivityComponent().inject(this);
        mMoviesListPresenter.onAttach(this);

        // Bind UI
        View v = inflater.inflate(R.layout.movies_list_fragment, container, false);
        RecyclerView moviesRecyclerView = v.findViewById(R.id.moviesListRecyclerView);

        mMoviesListGridLayout = new GridLayoutManager(this.getContext(),
                Constants.MOVIES_LIST_NO_OF_COLUMNS);

        moviesRecyclerView.setLayoutManager(mMoviesListGridLayout);
        mMoviesListAdapter = new MoviesListAdapter(this.getContext(), null);
        moviesRecyclerView.setAdapter(mMoviesListAdapter);
        moviesRecyclerView.addOnScrollListener(new MoviesListScrollListener());

        mMoviesListProgressBar = v.findViewById(R.id.moviesListProgressBar);

        setupSharedPreferences();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.movies_list_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.movies_list_settings_menu_item) {
            mMoviesListPresenter.onSettingsClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayMovies(@NonNull List<Movie> movies) {
        mMoviesListAdapter.setMovies(movies);
        mMoviesListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        mMoviesListProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mMoviesListProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setIsLoadingMovies(boolean isLoadingMovies) {
        isLoading = isLoadingMovies;
    }

    @Override
    public void resetAdapter() {
        mMoviesListAdapter.resetMoviesList();
    }

    @Override
    public void openSettings() {
        Intent settingsIntent = new Intent(requireActivity(), SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.sort_order_key))) {
            // Should list movies in the new order

            mSortCriteria = sharedPreferences.getString(
                    key,
                    getString(R.string.sort_by_popularity_value));

            mMoviesListPresenter.onSortCriteriaChange();

            fetchNextPageOfMovies();
        }
    }

    /**
     * Load preferences and register as a listener for any changes in preferences
     */
    private void setupSharedPreferences() {
        // Load preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                this.requireContext());
        mSortCriteria = sharedPreferences.getString(
                getString(R.string.sort_order_key),
                getString(R.string.sort_by_popularity_value));


        fetchNextPageOfMovies();

        // Register as a listener for any changes in preferences
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * Fetch movies based on the movies sort criteria
     */
    private void fetchMovies() {
        if (mSortCriteria.equals(getString(R.string.sort_by_rating_value))) {
            // Fetch top rated movies
            mMoviesListPresenter.onFetchTopRatedMovies();
        } else {
            // Fetch popular movies by default
            mMoviesListPresenter.onFetchPopularMovies();
        }
    }

    /**
     * Gets the next page of movies
     */
    private void fetchNextPageOfMovies() {
        isLoading = true;
        fetchMovies();
        mMoviesListPresenter.moveToNextPage();
    }

    private class MoviesListScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = mMoviesListGridLayout.getChildCount();
            int totalItemCount = mMoviesListGridLayout.getItemCount();
            int lastVisibleItemPosition = mMoviesListGridLayout.findLastVisibleItemPosition();

            Timber.d("Total item count: %s, Visble item count: %s, Last visible item pos: %s",
                    totalItemCount, visibleItemCount, lastVisibleItemPosition);

            if (!isLoading) {
                if (lastVisibleItemPosition >= (totalItemCount * Constants.SCROLL_PAGINATION_RATIO)
                        && visibleItemCount < totalItemCount) {
                    Timber.d("Fetch more movies now");
                    fetchNextPageOfMovies();
                }
            }
        }


    }
}
