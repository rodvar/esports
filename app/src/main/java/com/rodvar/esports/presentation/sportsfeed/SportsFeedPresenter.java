package com.rodvar.esports.presentation.sportsfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.feed.SportFeed;
import com.rodvar.esports.presentation.BasePresenter;

/**
 * Created by rodrigo on 29/11/16.
 */
public class SportsFeedPresenter extends BasePresenter implements API.Callback<SportFeed> {

    private static final String TAG = SportsFeedPresenter.class.getSimpleName();

    private SportFeed sportsFeed;
    private String feedUrl;

    public SportsFeedPresenter(API api, String url) {
        super(api);
        this.feedUrl = url;
    }

    @Override
    public void onSuccess(SportFeed model) {
        Log.d(TAG, "Success!");
        this.sportsFeed = model;
        this.getView().refresh();
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, "Failed! " + throwable.getLocalizedMessage());
        this.getView().toast(throwable.getLocalizedMessage());
    }

    @Override
    public Context getContext() {
        return this.getView().getContext();
    }

    @Override
    public void request() {
        this.getApi().getSportFeed(this, this.feedUrl);
    }

    @Override
    public int getItemCount() {
        return this.hasData() ? this.sportsFeed.size() : 0;
    }

    @Override
    public boolean hasData() {
        return this.sportsFeed != null;
    }

    @Override
    public void bind(RecyclerView.ViewHolder holder, int position) {
        // TODO
    }

    @Override
    public void onItemClick(int position) {
        // TODO
    }
}
