package com.rodvar.esports;

import android.app.Application;

import io.paperdb.Paper;

/**
 * Created by rodrigo on 30/11/16.
 * Provides app initialization
 */
public class ESportsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this.getApplicationContext());
    }
}
