package com.rodvar.esports.data;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by rodrigo on 28/11/16.
 * Generic error listener implementation
 */
public class GenericResponseErrorListener implements Response.ErrorListener {

    private API.Callback callback;

    public GenericResponseErrorListener(API.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        if (error instanceof ServerError && response != null) {
            try {
                String res = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                // Now you can use any deserializer to make sense of data
                error.addSuppressed(new IllegalStateException(res));
                this.callback.onFailure(error);
                Log.e("API", res);
            } catch (UnsupportedEncodingException e1) {
                Log.e("API", "Failed to decode on error response", e1);
                this.callback.onFailure(e1);
            }
        } else
            this.callback.onFailure(error);
    }
}
