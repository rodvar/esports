package com.rodvar.esports.presentation.sportslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rodvar.esports.R;
import com.rodvar.esports.presentation.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodrigo on 26/11/16.
 *
 * // TODO Refactor this to have a base adapter and view holder so as to only implement what is needed
 */
class SportsListAdapter extends RecyclerView.Adapter {

    private final BasePresenter presenter;

    SportsListAdapter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sports_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.presenter.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return this.presenter.getItemCount();
    }

    /**
     *
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        public TextView title;

        ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }

        @OnClick(R.id.title)
        void onClick(View view) {
            presenter.onItemClick(this.getPosition());
        }
    }
}
