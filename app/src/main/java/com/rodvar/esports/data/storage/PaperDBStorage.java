package com.rodvar.esports.data.storage;

import android.content.Context;

import io.paperdb.Paper;

/**
 * Created by rodrigo on 01/12/16.
 * Paper DB wrapper implementing defined DBStorage interface
 */
public class PaperDBStorage implements DBStorage {
    @Override
    public void init(Context context) {
        Paper.init(context);
    }

    @Override
    public <T> void write(String key, T value) {
        Paper.book().write(key, value);
    }

    @Override
    public <T> T read(String key) {
        return Paper.book().read(key);
    }
}
