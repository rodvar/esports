package com.rodvar.esports.data;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.rodvar.esports.data.model.SportList;

import java.io.UnsupportedEncodingException;
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
        RequestQueue queue = Volley.newRequestQueue(callback.getContext());
        String url = BASE_URL + "sports" + API_VERSION;
        SimpleXMLRequest<SportList> sportsRequest =
                new SimpleXMLRequest<>(Request.Method.GET, url, SportList.class, this.headers,
                        new Response.Listener<SportList>() {
                            @Override
                            public void onResponse(SportList response) {
                                if (response.isValid())
                                    callback.onSuccess(response);
                                else
                                    callback.onFailure(new IllegalStateException("invalid response"));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                NetworkResponse response = error.networkResponse;
                                if (error instanceof ServerError && response != null) {
                                    try {
                                        String res = new String(response.data,
                                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                        // Now you can use any deserializer to make sense of data
                                        error.addSuppressed(new IllegalStateException(res));
                                        callback.onFailure(error);
                                        Log.e("API", res);

                                    } catch (UnsupportedEncodingException e1) {
                                        // Couldn't properly decode data to string
                                        e1.printStackTrace();
                                    }
                                } else
                                    callback.onFailure(error);
                            }
                        }
                );
        queue.add(sportsRequest);
    }
}
