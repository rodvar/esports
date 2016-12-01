package com.rodvar.esports.data.model;

import java.io.Serializable;

/**
 * Created by rodrigo on 26/11/16.
 */
public interface AppModel extends Serializable {

    /**
     * @return true if the model has valid data
     */
    boolean isValid();

    /**
     * @return number of elements this model has to show. 1 is default (itself)
     */
    int size();
}
