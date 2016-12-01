package com.rodvar.esports.data.model.feed;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by rodrigo on 29/11/16.
 */
@Root(strict = false)
public class Summary {
    @Text
    private String content;
    @Attribute
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

    @Override
    public String toString() {
        return this.content;
    }
}