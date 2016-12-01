package com.rodvar.esports.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rodvar.esports.R;
import com.rodvar.esports.data.API;
import com.rodvar.esports.data.model.AppModel;
import com.rodvar.esports.data.storage.DBStorage;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Shared code between presenters
 */
public abstract class BasePresenter<T extends AppModel> implements AppPresenter, API.Callback<T> {

    private static final String TAG = BasePresenter.class.getSimpleName();
    private static final String BAD_ANDROID_APP = "Bad Android App";

    private final DBStorage storage;
    private final API api;
    private AppFragment view;

    private T model;

    /**
     * @param api     to use to get data from external service
     * @param storage to use for cache
     */
    public BasePresenter(API api, DBStorage storage) {
        this.api = api;
        this.storage = storage;
    }

    /**
     * Main behaviur is save model in memory and refresh the view.
     *
     * @param model the model retrieved after call execution
     */
    @Override
    public final void onSuccess(T model) {
        Log.d(TAG, "Success retrieving model !");
        this.model = model;
        if (this.hasData()) {
            this.getView().refresh();
            this.doOnSuccess();
        }
    }

    /**
     * Redefine in your presenter, getModel() and have fun! :)
     */
    protected void doOnSuccess() {
        // default
    }

    @Override
    public final void onFailure(Throwable throwable) {
        String errorMessage = throwable.getLocalizedMessage();
        if (errorMessage == null)
            errorMessage = throwable.getSuppressed()[0].getLocalizedMessage();
        if (errorMessage == null)
            errorMessage = this.getContext().getString(R.string.server_error);
        Log.e(TAG, "Failed! " + errorMessage, throwable);
        if (errorMessage.contains(BAD_ANDROID_APP))
            this.api.refreshHeaders();
        else
            this.doOnFailure(throwable, errorMessage);
    }

    /**
     * @param throwable    the error provided by the server service
     * @param errorMessage a readable error message
     */
    protected abstract void doOnFailure(Throwable throwable, String errorMessage);

    @Override
    public void onResume() {
        this.request();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onViewCreated() {
        // default
    }

    @Override
    public void saveInstanceState() {
        // default
    }

    @Override
    public void restoreInstanceState() {
        // default
    }

    @Override
    public void bindView(AppFragment fragment) {
        this.view = fragment;
    }

    /**
     * @param holder   holder to setup
     * @param position position of that holder in the recycler view
     */
    public abstract void bind(RecyclerView.ViewHolder holder, int position);

    /**
     * action on item click
     *
     * @param position the position of the clicked item
     */
    public abstract void onItemClick(int position);

    protected API getApi() {
        return this.api;
    }

    protected DBStorage getStorage() {
        return storage;
    }

    public AppFragment getView() {
        return view;
    }

    @Override
    public Context getContext() {
        return this.getView().getContext();
    }

    protected T getModel() {
        return model;
    }

    @Override
    public boolean hasData() {
        return this.model != null;
    }
}
