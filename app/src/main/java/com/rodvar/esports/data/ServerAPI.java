package com.rodvar.esports.data;

/**
 * Created by rodrigo on 26/11/16.
 * Implementation of the {@link API} using retrofit and Simple XML.
 */
public class ServerAPI implements API {

    private static ServerAPI instance = new ServerAPI();

    private ServerAPI() {

    }

    public static ServerAPI getInstance() {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }

    @Override
    public void getSports(Callback callback) {

    }
}
