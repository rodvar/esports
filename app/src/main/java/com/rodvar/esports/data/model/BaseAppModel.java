package com.rodvar.esports.data.model;

/**
 * Created by rodrigo on 26/11/16.
 */
public abstract class BaseAppModel implements AppModel {

    @Override
    public int size() {
        return 1; //default
    }
}
