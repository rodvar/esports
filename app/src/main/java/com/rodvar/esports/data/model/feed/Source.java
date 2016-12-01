package com.rodvar.esports.data.model.feed;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by rodrigo on 29/11/16.
 */
@Root(strict = false)
public class Source {
    private static final String RSS = "rss";

    @Element
    private String id;
    @Element
    private String title;
    @Element
    private String updated;
    @Element
    private String rights;

    public boolean isRSS() {
        return this.id.toLowerCase().contains(RSS);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

}