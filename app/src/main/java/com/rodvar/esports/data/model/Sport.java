package com.rodvar.esports.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by rodrigo on 27/11/16.
 */
@Root(strict = false)
public class Sport extends BaseAppModel implements ISport {

    @Attribute(name = "href")
    private String url;
    @Element(name = "id")
    private String id;
    @Element(name = "title")
    private String name;

    @Override
    public boolean isValid() {
        return id != null && !id.isEmpty();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
