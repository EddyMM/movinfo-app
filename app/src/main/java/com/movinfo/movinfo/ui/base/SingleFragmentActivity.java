package com.movinfo.movinfo.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.movinfo.movinfo.R;

/**
 * Base activity that hosts a single fragment
 */

public abstract class SingleFragmentActivity extends BaseActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.single_fragment);

        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.single_fragment, createFragment())
                    .commit();
        }
    }
}
