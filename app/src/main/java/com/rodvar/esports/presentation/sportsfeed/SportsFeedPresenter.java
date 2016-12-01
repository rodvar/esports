package com.rodvar.esports.presentation.sportsfeed;

import android.content.Context;
import android.util.Log;

import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.feed.Entry;
import com.rodvar.esports.data.model.feed.SportFeed;
import com.rodvar.esports.data.storage.DBStorage;
import com.rodvar.esports.presentation.BaseAdapter;
import com.rodvar.esports.presentation.BasePresenter;
import com.rodvar.esports.presentation.MainListFragment;

/**
 * Created by rodrigo on 29/11/16.
 */
public class SportsFeedPresenter extends BasePresenter<SportFeed> {

    private static final String TAG = SportsFeedPresenter.class.getSimpleName();
    private static final String SPORTS_FEED_KEY = "sports_feed_key";
    private static final String FEED_URL_KEY = "feed_url";

    private String feedUrl;

    public SportsFeedPresenter(API api, DBStorage storage, String url) {
        super(api, storage);
        this.feedUrl = url;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        ((MainListFragment) this.getView()).setAdapter(new SportsFeedAdapter(this));
    }

    @Override
    public void saveInstanceState() {
        super.saveInstanceState();
        if (this.feedUrl != null)
            this.getStorage().write(FEED_URL_KEY, this.feedUrl);
    }

    @Override
    public void restoreInstanceState() {
        super.restoreInstanceState();
        if (this.feedUrl == null)
            this.feedUrl = this.getStorage().read(FEED_URL_KEY);
    }

    @Override
    protected void doOnFailure(Throwable throwable, String errorMessage) {
        this.getView().toast(errorMessage);
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
    public void bind(BaseAdapter.ViewHolder holder, int position) {
        try {
            SportsFeedAdapter.ViewHolder myHolder = (SportsFeedAdapter.ViewHolder) holder;
            Entry feedEntry = this.getModel().get(position);
            myHolder.title.setText(feedEntry.getSummary().toString());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "model at position does not exist: " + position, e);
        } catch (Exception e) {
            Log.e(TAG, "Failed to bind holder with model", e);
        }
    }

    @Override
    public void onItemClick(int position) {
        // TODO
    }
}
