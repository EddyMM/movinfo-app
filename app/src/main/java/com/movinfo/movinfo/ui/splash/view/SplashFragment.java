package com.movinfo.movinfo.ui.splash.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movinfo.movinfo.R;
import com.movinfo.movinfo.ui.movies.list.view.MoviesListActivity;
import com.movinfo.movinfo.ui.splash.presenter.SplashMvpPresenter;

import javax.inject.Inject;

/**
 * Fragment to manage splash view
 */

public class SplashFragment extends Fragment implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> splashPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        // Inject member variables with objects
        ((SplashActivity) requireActivity()).getActivityComponent().inject(this);
        // Create presenter and attach mvp view
        splashPresenter.onAttach(this);

        View v = inflater.inflate(R.layout.splash_fragment, container, false);

        // Handle the start text and arrow using the same click listener
        Group startViews = v.findViewById(R.id.startGroup);
        for (int id : startViews.getReferencedIds()) {
            v.findViewById(id).setOnClickListener((view) -> splashPresenter.onSplashStartClick());
        }

        return v;
    }

    /**
     * Starts the movies list activity
     */
    @Override
    public void openMoviesList() {
        startActivity(MoviesListActivity.getIntent(this.getContext()));
    }

    @Override
    public void close() {
        requireActivity().finish();
    }
}
