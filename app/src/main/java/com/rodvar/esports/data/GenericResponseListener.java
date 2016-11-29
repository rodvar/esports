package com.rodvar.esports.data;

import com.android.volley.Response;
import com.rodvar.esports.data.model.BaseAppModel;

/**
 * Created by rodrigo on 28/11/16.
 * General implementation for response listener
 */
public class GenericResponseListener<T extends BaseAppModel> implements Response.Listener<T> {

    private final API.Callback callback;

    public GenericResponseListener(API.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(T response) {
        if (response.isValid())
            this.callback.onSuccess(response);
        else
            this.callback.onFailure(new IllegalStateException("invalid response"));
    }
}
