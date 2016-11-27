package com.rodvar.esports.presentation;

import android.content.Context;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Definition of this app fragments behaviour
 */
public interface AppFragment {

    Context getContext();

    int getTitleResId();

    void setActivity(Listener activity);

    void setPresenter(AppPresenter presenter);

    /**
     * executes view update
     */
    void refresh();

    /**
     * @param text text to show in a toast
     */
    void toast(String text);


    /**
     * Activities needs to implement this to be able to be called by fragments
     */
    interface Listener {
    }

}
