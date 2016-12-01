package com.rodvar.esports.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by rodrigo on 01/12/16.
 * Base adapter for this app. Resolves common problems related to MVP
 */
public abstract class BaseAdapter extends RecyclerView.Adapter {

    private final BasePresenter presenter;

    /**
     * @param presenter
     */
    protected BaseAdapter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * @return layout resource id for this adapter
     */
    protected abstract int getLayoutResId();

    /**
     * @param v
     * @return decide view holder implementation
     */
    protected abstract BaseAdapter.ViewHolder instantiateViewHolder(View v);

    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(this.getLayoutResId(), parent, false);
        return this.instantiateViewHolder(v);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.presenter.bind((ViewHolder) holder, position);
    }

    @Override
    public final int getItemCount() {
        return this.presenter.getItemCount();
    }

    /**
     * Base ViewHolder. Provides butterknife auto binding
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        protected ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }
    }
}
