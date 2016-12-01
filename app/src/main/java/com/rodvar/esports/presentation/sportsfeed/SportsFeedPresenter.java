package com.rodvar.esports.presentation.sportsfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.feed.SportFeed;
import com.rodvar.esports.data.storage.DBStorage;
import com.rodvar.esports.presentation.BasePresenter;
import com.rodvar.esports.presentation.MainListFragment;

/**
 * Created by rodrigo on 29/11/16.
 */
public class SportsFeedPresenter extends BasePresenter<SportFeed> {

    private static final String TAG = SportsFeedPresenter.class.getSimpleName();
    private static final String SPORTS_FEED_KEY = "sports_feed_key";
    private static final String FEED_URL_KEY = "feed_url";

    private SportFeed sportsFeed;
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
        if (this.sportsFeed != null)
            this.getStorage().write(SPORTS_FEED_KEY, this.sportsFeed);
    }

    @Override
    public void restoreInstanceState() {
        super.restoreInstanceState();
        if (this.feedUrl == null)
            this.feedUrl = this.getStorage().read(FEED_URL_KEY);
        if (this.sportsFeed == null)
            this.sportsFeed = this.getStorage().read(SPORTS_FEED_KEY);
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
