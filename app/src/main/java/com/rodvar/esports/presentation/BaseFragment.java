package com.rodvar.esports.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rodvar.esports.MainActivity;
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
    private int titleResId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_sports_list, null, false);
        ButterKnife.bind(this, this.rootView);
        return this.rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.getPresenter() != null)
            this.getPresenter().saveInstanceState();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (this.getPresenter() != null)
            this.getPresenter().restoreInstanceState();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.presenter != null)
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
    public MainActivity getMainActivity() {
        return (MainActivity) this.getActivity();
    }

    @Override
    public void setActivity(Listener activity) {
        this.activity = new WeakReference<>(activity);
    }

    protected AppPresenter getPresenter() {
        return this.presenter;
    }

    /**
     * Sets presenter and binds to this view
     *
     * @param presenter
     */
    @Override
    public void setPresenter(AppPresenter presenter) {
        this.presenter = presenter;
        this.presenter.bindView(this);
    }

    @Override
    public int getTitleResId() {
        return this.titleResId;
    }

    @Override
    public void setTitleResId(int resId) {
        this.titleResId = resId;
    }

    @Override
    public void toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
