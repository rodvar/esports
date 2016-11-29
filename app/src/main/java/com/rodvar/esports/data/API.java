package com.rodvar.esports.data;

import android.content.Context;

import com.rodvar.esports.data.model.AppModel;
import com.rodvar.esports.data.model.SportList;

/**
 * Created by rodrigo on 26/11/16.
 * <p>
 * Every API implementation must implement the following methods
 *
 *
 */
public interface API {
    /**
     * Gets the list of sports
     *
     */
    void getSports(Callback<SportList> callback);

    /**
     * @param callback
     * @param url      where the feed is
     */
    void getSportFeed(final API.Callback callback, String url);

    /**
     * Every call backer must implement this methods
     */
    interface Callback<T extends AppModel> {
        void onSuccess(T model);

        void onFailure(Throwable throwable);

        Context getContext();
    }

}
