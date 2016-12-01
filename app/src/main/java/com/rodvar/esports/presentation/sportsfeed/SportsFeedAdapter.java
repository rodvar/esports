package com.rodvar.esports.presentation.sportsfeed;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.rodvar.esports.presentation.BasePresenter;

/**
 * Created by rodrigo on 30/11/16.
 */
class SportsFeedAdapter extends RecyclerView.Adapter {
    private final BasePresenter presenter;

    SportsFeedAdapter(BasePresenter sportsFeedPresenter) {
        this.presenter = sportsFeedPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
