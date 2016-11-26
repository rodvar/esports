package com.rodvar.esports.presentation;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Shared code between presenters
 */
public abstract class BasePresenter implements AppPresenter {

    private AppFragment view;

    @Override
    public void bindView(AppFragment fragment) {
        this.view = fragment;
    }
}
