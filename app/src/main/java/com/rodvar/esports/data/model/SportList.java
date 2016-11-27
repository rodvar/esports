package com.rodvar.esports.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;


/**
 * Created by rodrigo on 26/11/16.
 */
@Root(strict = false)
public class SportList extends BaseAppModel {
    @Element
    private Workspace workspace;

    private String xmlns;

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    @Override
    public String toString() {
        return "ClassPojo [workspace = " + workspace + ", xmlns = " + xmlns + "]";
    }

    @Override
    public boolean isValid() {
        return this.workspace != null;
    }

    public int size() {
        return this.getSports().size();
    }

    private List<Sport> getSports() {
        return this.getWorkspace().getSports();
    }

    public ISport get(int position) {
        return this.getSports().get(position);
    }
}
