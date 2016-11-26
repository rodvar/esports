package com.rodvar.esports.data.model;

import java.util.List;

/**
 * Created by rodrigo on 26/11/16.
 */
public class SportList extends BaseAppModel {
    private List<ISport> list;

    public SportList(List<ISport> sports) {
        this.list = sports;
    }

    public ISport get(int position) {
        return this.list.get(position);
    }

    public int size() {
        return this.list.size();
    }
}
