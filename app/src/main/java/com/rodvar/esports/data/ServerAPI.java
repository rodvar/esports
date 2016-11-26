package com.rodvar.esports.data;

import com.rodvar.esports.data.model.ISport;
import com.rodvar.esports.data.model.SportList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<ISport> sports = new ArrayList<>();
        sports.add(new ISport() {
            @Override
            public String getName() {
                return "OH LA LA" + new Random().nextInt(10);
            }
        });
        sports.add(new ISport() {
            @Override
            public String getName() {
                return "EMAB";
            }
        });
        callback.onSuccess(new SportList(sports));
    }
}
