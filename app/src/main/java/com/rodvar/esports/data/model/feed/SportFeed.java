package com.rodvar.esports.data.model.feed;

import com.rodvar.esports.data.model.BaseAppModel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by rodrigo on 29/11/16.
 */
@Root(strict = false)
public class SportFeed extends BaseAppModel {
    @Element
    private String id;
    @Element
    private String updated;
    @ElementList(entry = "entry", inline = true)
    private List<Entry> entries;
    @Element
    private Title title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }


    public int size() {
        return this.entries.size();
    }

    @Override
    public boolean isValid() {
        return this.entries != null;
    }
}
