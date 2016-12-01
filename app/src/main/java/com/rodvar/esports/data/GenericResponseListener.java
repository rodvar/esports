package com.rodvar.esports.data;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Response;
import com.rodvar.esports.data.model.BaseAppModel;
import com.rodvar.esports.data.storage.DBStorage;

/**
 * Created by rodrigo on 28/11/16.
 * General implementation for response listener. Saves successful response to database
 */
public class GenericResponseListener<T extends BaseAppModel> implements Response.Listener<T> {

    private static final String TAG = GenericResponseListener.class.getSimpleName();

    private final API.Callback callback;
    private final DBStorage storage;
    private final String url;

    public GenericResponseListener(API.Callback callback, DBStorage storage, String url) {
        this.callback = callback;
        this.storage = storage;
        this.url = url;
    }

    @Override
    public void onResponse(T response) {
        if (response.isValid()) {
            this.saveResponse(response);
            this.callback.onSuccess(response);
        } else
            this.callback.onFailure(new IllegalStateException("invalid response"));
    }

    private void saveResponse(final T response) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    storage.write(String.valueOf(url.hashCode()), response);
                    Log.d(TAG, "successful response cached successfully under url " + url);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to cache response under url " + url, e);
                }
                return null;
            }
        }.execute();
    }
}
