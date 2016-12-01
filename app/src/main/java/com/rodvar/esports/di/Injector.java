package com.rodvar.esports.di;

import com.rodvar.esports.R;
import com.rodvar.esports.data.API;
import com.rodvar.esports.data.GenericResponseErrorListener;
import com.rodvar.esports.data.GenericResponseListener;
import com.rodvar.esports.data.ServerAPI;
import com.rodvar.esports.data.storage.DBStorage;
import com.rodvar.esports.data.storage.PaperDBStorage;
import com.rodvar.esports.presentation.AppFragment;
import com.rodvar.esports.presentation.MainListFragment;
import com.rodvar.esports.presentation.sportscontent.WebContentFragment;
import com.rodvar.esports.presentation.sportsfeed.SportsFeedPresenter;
import com.rodvar.esports.presentation.sportslist.SportsListPresenter;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Simple instance provider
 */
public class Injector {

    private static Injector instance = new Injector();

    private final DBStorage storage;

    private Injector() {
        this.storage = new PaperDBStorage();
    }

    public static Injector getInstance() {
        return instance;
    }

    public MainListFragment instantiateSportListFragment(AppFragment.Listener activity) {
        return MainListFragment.instantiate(activity, this.instantiateSportsListPresenter()
                , R.string.title_sports_list);
    }

    public AppFragment instantiateFeedFragment(AppFragment.Listener listener, String url) {
        return MainListFragment.instantiate(listener, this.instantiateSportsFeedPresenter(url)
                , R.string.title_sport_feed);
    }

    public AppFragment instantiateWebViewFragment(AppFragment.Listener listener, String url) {
        return WebContentFragment.instantiate(listener, url);
    }

    private SportsFeedPresenter instantiateSportsFeedPresenter(String url) {
        return new SportsFeedPresenter(ServerAPI.getInstance(), this.storage, url);
    }

    private SportsListPresenter instantiateSportsListPresenter() {
        return new SportsListPresenter(ServerAPI.getInstance(), this.storage);
    }

    public GenericResponseListener instantiateResponseListener(API.Callback callback, String url) {
        return new GenericResponseListener(callback, this.storage, url);
    }

    public GenericResponseErrorListener instantiateResponseErrorListener(API.Callback callback, String url) {
        return new GenericResponseErrorListener(callback, this.storage, url);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }
}
