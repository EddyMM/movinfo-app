package com.movinfo.movinfo;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movinfo.movinfo.ui.splash.view.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test if the splash activity renders UI correctly and responds correctly
 */

@RunWith(AndroidJUnit4.class)
public class SplashScreenTest {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule =
            new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void testStartText() {
        onView(withId(R.id.startTextView))
                .perform(click());
        onView(withId(R.id.moviesListRecyclerView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testStartImage() {
        onView(withId(R.id.startImageView))
                .perform(click());
        onView(withId(R.id.moviesListRecyclerView))
                .check(matches(isDisplayed()));
    }
}
