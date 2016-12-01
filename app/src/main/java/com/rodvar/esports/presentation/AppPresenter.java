package com.rodvar.esports.presentation;

/**
 * Created by rodrigo on 26/11/16.
 * Expected behaviour for this app presenter. Each presentar has one main API call to execute.
 */
public interface AppPresenter {

    void bindView(AppFragment fragment);

    void onResume();

    void onDestroy();

    /**
     * Execute this presenter request
     */
    void request();

    /**
     * @return number of that items fetched at the moment
     */
    int getItemCount();

    /**
     * @return true if the presenter has data to present
     */
    boolean hasData();

    /**
     * Code for saving instance state goes here
     */
    void saveInstanceState();

    /**
     * Code for restoring instance state goes here
     */
    void restoreInstanceState();
}
