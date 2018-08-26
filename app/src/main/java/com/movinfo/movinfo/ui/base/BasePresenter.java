package com.movinfo.movinfo.ui.base;

import com.movinfo.movinfo.data.DataManager;

import javax.inject.Inject;

/**
 *
 */

public class BasePresenter {
    private DataManager mDataManager;

    @Inject
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }
}
