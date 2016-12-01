package com.rodvar.esports;

import android.app.Application;

import com.rodvar.esports.data.storage.PaperDBStorage;

/**
 * Created by rodrigo on 30/11/16.
 * Provides app initialization
 */
public class ESportsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new PaperDBStorage().init(this.getApplicationContext());
    }
}
