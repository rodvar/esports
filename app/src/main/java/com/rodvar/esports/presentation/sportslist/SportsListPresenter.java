package com.rodvar.esports.presentation.sportslist;

import android.util.Log;

import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.SportList;
import com.rodvar.esports.presentation.BasePresenter;

/**
 * Created by rodrigo on 26/11/16.
 */
public class SportsListPresenter extends BasePresenter implements API.Callback<SportList> {

    private static final String TAG = SportsListPresenter.class.getSimpleName();

    public SportsListPresenter(API api) {
        super(api);
    }

    @Override
    public void request() {
        this.getApi().getSports(this);
    }

    @Override
    public void onSuccess(SportList model) {
        Log.d(TAG, "Success!");
    }

    @Override
    public void onFailure(SportList model) {
        Log.d(TAG, "Failed!");
    }
}
