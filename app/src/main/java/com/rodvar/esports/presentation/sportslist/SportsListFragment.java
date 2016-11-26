package com.rodvar.esports.presentation.sportslist;

import com.rodvar.esports.MainActivity;
import com.rodvar.esports.presentation.AppFragment;
import com.rodvar.esports.presentation.BaseFragment;

/**
 * Created by rodrigo on 26/11/16.
 * Sports list view responsible for presenting the sports to the user and handle UI interactions
 */
public class SportsListFragment extends BaseFragment {

    public SportsListFragment() {

    }

    public static SportsListFragment instantiate(Listener activity
            , SportsListPresenter presenter) {
        SportsListFragment fragment = new SportsListFragment();
        fragment.setActivity(activity);
        fragment.setPresenter(presenter);
        return fragment;
    }
}
