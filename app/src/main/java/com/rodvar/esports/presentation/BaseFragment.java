package com.rodvar.esports.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodvar.esports.R;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by rodrigo on 26/11/16.
 *
 * Shared code between fragment views
 */
public abstract class BaseFragment extends Fragment implements AppFragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    private View rootView;

    private AppPresenter presenter;
    private WeakReference<Listener> activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_sports_list, null, false);
        ButterKnife.bind(this, this.rootView);
        return this.rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.onResume();
    }

    @Override
    public void onDestroy() {
        try {
            this.presenter.onDestroy();
            this.presenter = null;
        } catch (Exception e) {
            Log.e(TAG, "Failed to destroy presenter", e);
        }
        super.onDestroy();
    }

    @Override
    public void setActivity(Listener activity) {
        this.activity = new WeakReference<>(activity);
    }

    protected AppPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void setPresenter(AppPresenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }
}
