package com.rodvar.esports.presentation;

import android.support.v7.widget.RecyclerView;

import com.rodvar.esports.data.API;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Shared code between presenters
 */
public abstract class BasePresenter implements AppPresenter {

    private final API api;
    private AppFragment view;

    public BasePresenter(API api) {
        this.api = api;
    }

    @Override
    public void onResume() {
        this.request();
    }

    @Override
    public void onDestroy() {
        this.view = null;
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

    public AppFragment getView() {
        return view;
    }
}
