package com.rodvar.esports.data.model.feed;

import com.rodvar.esports.data.model.BaseAppModel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by rodrigo on 29/11/16.
 */
@Root(strict = false)
public class Entry extends BaseAppModel {

    @Element
    private String id;
    @Element
    private Summary summary;
    @Element
    private Title title;
    @Element
    private Source source;
    @Element
    private String updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Summary getSummary() {
        return summary;
    }

    @Override
    public boolean isValid() {
        return this.summary != null;
    }

    public Source getSource() {
        return source;
    }
}
