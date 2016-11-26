package com.rodvar.esports.presentation.sportslist;

import android.util.Log;

import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.ISport;
import com.rodvar.esports.data.model.SportList;
import com.rodvar.esports.presentation.BasePresenter;

/**
 * Created by rodrigo on 26/11/16.
 * Presenter that executes simple sports list call and tell the view to show them
 */
public class SportsListPresenter extends BasePresenter implements API.Callback<SportList> {

    private static final String TAG = SportsListPresenter.class.getSimpleName();
    private SportList sportsList;

    public SportsListPresenter(API api) {
        super(api);
    }

    @Override
    public void request() {
        this.getApi().getSports(this);
    }

    @Override
    public int getItemCount() {
        return this.hasData() ? this.sportsList.size() : 0;
    }

    @Override
    public boolean hasData() {
        return this.sportsList != null;
    }

    @Override
    public void onSuccess(SportList model) {
        Log.d(TAG, "Success!");
        this.sportsList = model;
        this.getView().refresh();
    }

    @Override
    public void onFailure(SportList model) {
        Log.d(TAG, "Failed!");
    }

    public void bind(SportsListAdapter.ViewHolder holder, int position) {
        try {
            ISport sport = this.sportsList.get(position);
            holder.title.setText(sport.getName());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "model at position does not exist: " + position, e);
        } catch (Exception e) {
            Log.e(TAG, "Failed to bind holder with model", e);
        }
    }
}
