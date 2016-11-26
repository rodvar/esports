package com.rodvar.esports.presentation.sportslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rodvar.esports.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodrigo on 26/11/16.
 */
public class SportsListAdapter extends RecyclerView.Adapter {

    private final SportsListPresenter presenter;

    public SportsListAdapter(SportsListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sports_list, parent, false);
        RecyclerView.ViewHolder vh = new ViewHolder(v);
        // Configure smth else..?
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.presenter.bind((ViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return this.presenter.getItemCount();
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        @BindView(R.id.title)
        public TextView title;

        public ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }
    }
}
