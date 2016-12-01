package com.rodvar.esports.data.storage;

import android.content.Context;

/**
 * Created by rodrigo on 01/12/16.
 * DBStorage interface for the DB implementations used. Basically we define a key value pair saving
 */
public interface DBStorage {

    /**
     * @param context init database storage
     */
    void init(Context context);

    /**
     * Write key value pair to database
     *
     * @param key   string key asociated with object
     * @param value actual object to write
     * @param <T>   type of object to write
     */
    <T> void write(String key, T value);

    /**
     * Retrieve value associated with key from DB
     *
     * @param key string key associated with object
     * @param <T> type of object to write
     * @return retrieve the object
     */
    <T> T read(String key);
}
