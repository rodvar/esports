package com.rodvar.esports.di;

import com.rodvar.esports.data.ServerAPI;
import com.rodvar.esports.presentation.AppFragment;
import com.rodvar.esports.presentation.sportslist.SportsListFragment;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }

    public SportsListFragment instantiateSportListFragment(AppFragment.Listener activity) {
        return SportsListFragment.instantiate(activity, this.instantiateSportsListPresenter());
    }

    private SportsListPresenter instantiateSportsListPresenter() {
        return new SportsListPresenter(ServerAPI.getInstance());
    }
}
