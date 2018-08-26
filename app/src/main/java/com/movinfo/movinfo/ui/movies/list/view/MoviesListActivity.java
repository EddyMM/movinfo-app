package com.movinfo.movinfo.ui.movies.list.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.movinfo.movinfo.ui.base.SingleFragmentActivity;

/**
 * Activity to manage the list of movies
 */

public class MoviesListActivity extends SingleFragmentActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, MoviesListActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return new MoviesListFragment();
    }
}
