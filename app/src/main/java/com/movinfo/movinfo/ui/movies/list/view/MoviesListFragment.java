package com.movinfo.movinfo.ui.movies.list.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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
import com.movinfo.movinfo.data.network.models.MoviesResponse;
import com.movinfo.movinfo.ui.movies.list.presenter.MoviesListAdapter;
import com.movinfo.movinfo.ui.movies.list.presenter.MoviesListPresenter;
import com.movinfo.movinfo.ui.movies.list.settings.SettingsActivity;
import com.movinfo.movinfo.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Fragment to host and manage movies list
 */

public class MoviesListFragment extends Fragment implements MoviesListMvpView,
        SharedPreferences.OnSharedPreferenceChangeListener,
        LoaderManager.LoaderCallbacks<Response<MoviesResponse>> {
    private static final int MOVIES_LIST_LOADER_MANAGER_ID = 17;
    private static final String MOVIES_LIST_SORT_CRITERIA = "SortCriteria";

    @Inject
    MoviesListPresenter<MoviesListMvpView> mMoviesListPresenter;
    private ProgressBar mMoviesListProgressBar;
    private MoviesListAdapter mMoviesListAdapter;
    private GridLayoutManager mMoviesListGridLayout;
    private Snackbar mInternetConnectionSnackbar;
    private boolean isLoading;
    private boolean retryAttempted;
    private String mSortCriteria;

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
        View view = inflater.inflate(R.layout.movies_list_fragment, container, false);
        RecyclerView moviesRecyclerView = view.findViewById(R.id.moviesListRecyclerView);

        mMoviesListGridLayout = new GridLayoutManager(this.getContext(),
                Constants.MOVIES_LIST_NO_OF_COLUMNS);

        moviesRecyclerView.setLayoutManager(mMoviesListGridLayout);
        mMoviesListAdapter = new MoviesListAdapter(this.getContext());
        moviesRecyclerView.setAdapter(mMoviesListAdapter);
        moviesRecyclerView.addOnScrollListener(new MoviesListScrollListener());

        mMoviesListProgressBar = view.findViewById(R.id.moviesListProgressBar);

        // Read preferences and load UI accordingly
        setupSharedPreferences();

        return view;
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
    public boolean isInternetConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) requireActivity().getSystemService(
                        Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Displays a SnackBar prompting user to connect to the internet in order to load movies
     */
    @Override
    public void showNoInternetConnectionMessage() {
        if (mInternetConnectionSnackbar == null || retryAttempted) {
            // Reset the retry attempt to ensure recursive retries are recognized
            retryAttempted = false;

            mInternetConnectionSnackbar = Snackbar.make(
                    requireActivity().findViewById(R.id.single_fragment),
                    getString(R.string.no_internet_connection_message), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.retry), (v) -> {
                        if (!isInternetConnected()) {
                            retryAttempted = true;
                            showNoInternetConnectionMessage();
                            return;
                        }
                        fetchNextPageOfMovies();
                    });
        }

        if (!mInternetConnectionSnackbar.isShown()) {
            mInternetConnectionSnackbar.show();
        }
    }

    /**
     * Removes the SnackBar prompting user to connect to the internet
     */
    @Override
    public void removeNoInternetConnectionMessage() {
        if (mInternetConnectionSnackbar != null && mInternetConnectionSnackbar.isShown()) {
            mInternetConnectionSnackbar.dismiss();
        }
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

            mMoviesListPresenter.refetchMovies();

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


        // Go ahead and fetch movies after reading preferred sort criteria
        fetchNextPageOfMovies();

        // Register as a listener for any changes in preferences
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * Gets the next page of movies based on the movies sort criteria
     */
    private void fetchNextPageOfMovies() {
        setIsLoadingMovies(true);

        // Ensure device is connected to the internet
        if (!isInternetConnected()) {
            setIsLoadingMovies(false);
            showNoInternetConnectionMessage();
            return;
        }

        // Dismiss the no internet connection if it is shown
        removeNoInternetConnectionMessage();

        mMoviesListPresenter.onFetchMovies();
    }

    /**
     * Scroll Listener to implement pagination (fetch more movies as the
     * user scrolls through them)
     */
    private class MoviesListScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            // Obtain some info about the number of items displayed already
            int visibleItemCount = mMoviesListGridLayout.getChildCount();
            int totalItemCount = mMoviesListGridLayout.getItemCount();
            int lastVisibleItemPosition = mMoviesListGridLayout.findLastVisibleItemPosition();

            Timber.d("Total item count: %s, Visible item count: %s, Last visible item pos: %s",
                    totalItemCount, visibleItemCount, lastVisibleItemPosition);

            // Apply pagination only if movies are not already being fetched as scrolling happens
            if (!isLoading) {
                if (lastVisibleItemPosition >= (totalItemCount * Constants.SCROLL_PAGINATION_RATIO)
                        && visibleItemCount < totalItemCount) {
                    Timber.d("Fetching more movies");
                    fetchNextPageOfMovies();
                }
            }
        }
    }

    /**
     * Use Loader Manager to load popular movies
     */
    @Override
    public void loadMovies() {
        LoaderManager loaderManager = requireActivity().getSupportLoaderManager();
        Loader loader = loaderManager.getLoader(MOVIES_LIST_LOADER_MANAGER_ID);

        Bundle bundle = new Bundle();
        bundle.putString(MOVIES_LIST_SORT_CRITERIA, mSortCriteria);

        if (loader == null) {
            loaderManager.initLoader(MOVIES_LIST_LOADER_MANAGER_ID, bundle, this);
        } else {
            loaderManager.restartLoader(MOVIES_LIST_LOADER_MANAGER_ID, bundle, this);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<Response<MoviesResponse>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Response<MoviesResponse>>(requireContext()) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                showProgressBar();

                forceLoad();
            }

            @Nullable
            @Override
            public Response<MoviesResponse> loadInBackground() {
                String sortCriteria = args.getString(MOVIES_LIST_SORT_CRITERIA,
                        getString(R.string.sort_by_popularity_value));

                if (sortCriteria.equals(getString(R.string.sort_by_rating_value))) {
                    // Fetch top rated movies
                    return mMoviesListPresenter.getTopRatedMovies();
                } else {
                    // Fetch popular movies by default
                    return mMoviesListPresenter.getPopularMovies();
                }
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Response<MoviesResponse>> loader,
            Response<MoviesResponse> moviesResponse) {
        mMoviesListPresenter.onFinishedLoadingMovies(moviesResponse);
        mMoviesListPresenter.moveToNextPage();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Response<MoviesResponse>> loader) {

    }
}
