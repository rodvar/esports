package com.rodvar.esports.data;

import com.rodvar.esports.data.model.AppModel;
import com.rodvar.esports.data.model.SportList;

/**
 * Created by rodrigo on 26/11/16.
 * <p>
 * Every API implementation must implement the following methods
 */
public interface API {
    /**
     * Gets the list of sports
     *
     * @param callback to be called after task is complete
     */
    void getSports(Callback<SportList> callback);

    /**
     * Every call backer must implement this methods
     */
    interface Callback<T extends AppModel> {
        void onSuccess(T model);

        void onFailure(T model);
    }

}
