package com.rodvar.esports.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.quasar.android.useragent.UserAgentFactory;
import com.rodvar.esports.data.model.SportList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rodrigo on 26/11/16.
 * Implementation of the {@link API} using retrofit and Simple XML.
 */
public class ServerAPI implements API {

    private static final String BASE_URL = "http://feed.esportsreader.com/reader/";
    private static final String API_VERSION = "?v=11";

    private static ServerAPI instance = new ServerAPI();
    private Map<String, String> headers = new HashMap<>();

    private ServerAPI() {
        this.headers.put("Accept", "application/atomsvc+xml; charset=utf-8");
    }

    public static ServerAPI getInstance() {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }

    @Override
    public void getSports(final API.Callback callback) {
        this.executeVolleyCall(callback, this.generateUrl("sports"), SportList.class);
    }

    /**
     * Executes the xml request using volley lib
     *
     * @param callback   provides context and what to do after call execution
     * @param url        to call
     * @param modelClass for response mapping
     */
    private void executeVolleyCall(Callback callback, String url, Class modelClass) {
        this.updateUserAgent(callback.getContext());
        final SimpleXMLRequest request =
                new SimpleXMLRequest(Request.Method.GET, url, modelClass, this.headers,
                        new GenericResponseListener(callback),
                        new GenericResponseErrorListener(callback)
                );
        final RequestQueue queue = Volley.newRequestQueue(callback.getContext());
        queue.add(request);
    }

    /**
     * @param relativeUrl relative url where to access
     * @return the URL ready to be used
     */
    @NonNull
    private String generateUrl(String relativeUrl) {
        return BASE_URL + relativeUrl + API_VERSION;
    }

    /**
     * Update headers with user agent if needed
     *
     * @param context
     */
    private void updateUserAgent(Context context) {
        final String userAgentKey = "User-agent";
        if (!this.headers.containsKey(userAgentKey))
            this.headers.put(userAgentKey, UserAgentFactory.createUserAgent(context));
    }
}
