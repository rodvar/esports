package com.rodvar.esports.presentation;

import com.rodvar.esports.MainActivity;
import com.rodvar.esports.presentation.sportslist.SportsListPresenter;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Definition of this app fragments behaviour
 */
public interface AppFragment {

    /**
     * Activities needs to implement this to be able to be called by fragments
     */
    interface Listener {
    }

    void setActivity(Listener activity);

    void setPresenter(AppPresenter presenter);

}
