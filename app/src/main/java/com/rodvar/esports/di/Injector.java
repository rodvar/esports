package com.rodvar.esports.di;

import com.rodvar.esports.R;
import com.rodvar.esports.data.ServerAPI;
import com.rodvar.esports.presentation.AppFragment;
import com.rodvar.esports.presentation.MainListFragment;
import com.rodvar.esports.presentation.sportsfeed.SportsFeedPresenter;
import com.rodvar.esports.presentation.sportslist.SportsListPresenter;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Simple instance provider
 */
public class Injector {

    private static Injector instance = new Injector();

    private Injector() {

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

    private SportsFeedPresenter instantiateSportsFeedPresenter(String url) {
        return new SportsFeedPresenter(ServerAPI.getInstance(), url);
    }

    private SportsListPresenter instantiateSportsListPresenter() {
        return new SportsListPresenter(ServerAPI.getInstance());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }
}
