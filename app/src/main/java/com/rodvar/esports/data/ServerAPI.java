package com.rodvar.esports.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.quasar.android.useragent.UserAgentFactory;
import com.rodvar.esports.data.model.SportList;
import com.rodvar.esports.data.model.feed.SportFeed;
import com.rodvar.esports.di.Injector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rodrigo on 26/11/16.
 * Implementation of the {@link API} using retrofit and Simple XML.
 */
public class ServerAPI implements API {

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Linux; Android" +
            "4.4.4;Google Nexus 5; Build/KTU84P; DPI/XHDPI; AppVersion/11) " +
            "AppleWebKit/537.36(KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile " +
            "Safari/537.36”";

    private static final String BASE_URL = "http://feed.esportsreader.com/reader/";
    private static final String API_VERSION = "?v=11";

    private static ServerAPI instance = new ServerAPI();
    private Map<String, String> headers = new HashMap<>();
    private boolean useDefaultUA = false;

    private ServerAPI() {
        VolleyLog.DEBUG = true;
        this.refreshHeaders();
    }

    public static ServerAPI getInstance() {
        return instance;
    }

    @Override
    public void getSports(final API.Callback callback) {
        this.executeVolleyCall(callback, this.generateUrl("sports"), SportList.class);
    }

    @Override
    public void getSportFeed(final API.Callback callback, String url) {
        this.executeVolleyCall(callback, this.appendApiVersion(url), SportFeed.class);
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
                        Injector.getInstance().instantiateResponseListener(callback, url),
                        Injector.getInstance().instantiateResponseErrorListener(callback, url)
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
        return this.appendApiVersion(BASE_URL + relativeUrl);
    }

    private String appendApiVersion(String url) {
        return url + API_VERSION;
    }

    /**
     * Update headers with user agent if needed
     *
     * @param context caller aos context
     */
    private void updateUserAgent(Context context) {
        final String userAgentKey = "User-agent";
        if (!this.headers.containsKey(userAgentKey)) {
            String userAgent = UserAgentFactory.createUserAgent(context);
            if (this.useDefaultUA || userAgent == null || userAgent.isEmpty())
                userAgent = DEFAULT_USER_AGENT;
            Log.d("API", "UA=" + userAgent);
            this.headers.put(userAgentKey, userAgent);
        }
    }

    @Override
    public void refreshHeaders() {
        Log.d("API", "Refreshing headers");
        this.headers = new HashMap<>();
        this.headers.put("Accept", "application/atomsvc+xml; charset=utf-8");
        this.useDefaultUA = !this.useDefaultUA;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }
}
