package com.rodvar.esports.presentation.sportslist;

import android.util.Log;

import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.ISport;
import com.rodvar.esports.data.model.SportList;
import com.rodvar.esports.data.storage.DBStorage;
import com.rodvar.esports.presentation.BaseAdapter;
import com.rodvar.esports.presentation.BasePresenter;
import com.rodvar.esports.presentation.MainListFragment;

/**
 * Created by rodrigo on 26/11/16.
 * Presenter that executes simple sports list call and tell the view to show them
 */
public class SportsListPresenter extends BasePresenter<SportList> {

    private static final String TAG = SportsListPresenter.class.getSimpleName();

    public SportsListPresenter(API api, DBStorage storage) {
        super(api, storage);
    }

    @Override
    protected void doOnFailure(Throwable throwable, String errorMessage) {
        this.getView().toast(errorMessage);
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        ((MainListFragment) this.getView()).setAdapter(new SportsListAdapter(this));
    }

    @Override
    public void request() {
        this.getApi().getSports(this);
    }

    @Override
    public void bind(BaseAdapter.ViewHolder holder, int position) {
        try {
            SportsListAdapter.ViewHolder myHolder = (SportsListAdapter.ViewHolder) holder;
            ISport sport = this.getModel().get(position);
            myHolder.title.setText(sport.getName());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "model at position does not exist: " + position, e);
        } catch (Exception e) {
            Log.e(TAG, "Failed to bind holder with model", e);
        }
    }

    @Override
    public void onItemClick(int position) {
        try {
            ISport sport = this.getModel().get(position);
            this.getView().toast("Navigating to feed " + sport.getName());
            this.getView().getMainActivity().navigateFeed(sport.getUrl());
        } catch (Exception e) {
            Log.d(TAG, "Failed on item click " + position, e);
        }
    }
}
