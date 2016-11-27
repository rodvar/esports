package com.rodvar.esports.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by rodrigo on 26/11/16.
 */
@Root(strict = false)
public class SportList extends BaseAppModel {
    //@ElementUnion({
    //        @Element(name="collection", type=Sport.class),
    //})
    @Element(name = "title")
    private String title;
    @ElementList(name = "collection", entry = "collection", inline = true, type = Sport.class, required = false)
    private List<Sport> list;

    public SportList() {

    }

    public SportList(List<Sport> sports) {
        this();
        this.list = sports;
    }

    public ISport get(int position) {
        return this.list.get(position);
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isValid() {
        return this.list != null;
    }
}
