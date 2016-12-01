package com.rodvar.esports.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 27/11/16.
 */
@Root(strict = false)
public class Workspace {

    @ElementList(entry = "collection", inline = true)
    private List<Sport> sports;
    @Element
    private String title;

    public Workspace() {
        this.sports = new ArrayList<>();
    }

    public Workspace(List<Sport> sports) {
        this();
        this.sports = sports;
    }

    public List<Sport> getSports() {
        return this.sports;
    }
}