package com.rodvar.esports.data;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.rodvar.esports.data.model.AppModel;
import com.rodvar.esports.data.storage.DBStorage;

import java.io.UnsupportedEncodingException;

/**
 * Created by rodrigo on 28/11/16.
 * Generic error listener implementation
 */
public class GenericResponseErrorListener implements Response.ErrorListener {

    private final static String TAG = GenericResponseErrorListener.class.getSimpleName();

    private final DBStorage storage;
    private final String url;
    private API.Callback callback;

    public GenericResponseErrorListener(API.Callback callback, DBStorage storage, String url) {
        this.callback = callback;
        this.storage = storage;
        this.url = url;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        if (error instanceof ServerError && response != null) {
            try {
                AppModel cachedData = this.storage.read(String.valueOf(this.url.hashCode()));
                if (cachedData == null) {
                    String res = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                    // Now you can use any deserializer to make sense of data
                    error.addSuppressed(new IllegalStateException(res));
                    this.callback.onFailure(error);
                    Log.e(TAG, res);
                } else {
                    Log.d(TAG, "Failed to retrieve from server. Using Cache..");
                    this.callback.onSuccess(cachedData);
                }
            } catch (UnsupportedEncodingException e1) {
                Log.e("API", "Failed to decode on error response", e1);
                this.callback.onFailure(e1);
            }
        } else
            this.callback.onFailure(error);
    }
}
