package com.rodvar.esports.presentation;

import android.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Shared code between fragment views
 */
public abstract class BaseFragment extends Fragment implements AppFragment {

    private AppPresenter presenter;
    private WeakReference<Listener> activity;

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.onResume();
    }

    @Override
    public void onDestroy() {
        this.presenter.onDestroy();
        this.presenter = null;
        super.onDestroy();
    }

    @Override
    public void setActivity(Listener activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public void setPresenter(AppPresenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
