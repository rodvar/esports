package com.rodvar.esports.data.model.feed;

import org.simpleframework.xml.Root;

/**
 * Created by rodrigo on 29/11/16.
 */
@Root(strict = false)
public class Title {
    private String content;

    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

