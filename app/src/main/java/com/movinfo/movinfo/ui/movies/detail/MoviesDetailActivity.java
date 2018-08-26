package com.movinfo.movinfo.ui.movies.detail;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;

import com.movinfo.movinfo.ui.base.SingleFragmentActivity;

/**
 * @author eddy.
 */

public class MoviesDetailActivity extends SingleFragmentActivity {
    public static final String MOVIE_INTENT_EXTRA = "MOVIE_INTENT_EXTRA";

    @Override
    protected Fragment createFragment() {
        return new MoviesDetailFragment();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
