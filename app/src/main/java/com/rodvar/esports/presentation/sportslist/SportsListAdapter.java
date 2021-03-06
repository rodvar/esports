package com.rodvar.esports.presentation.sportslist;

import android.view.View;
import android.widget.TextView;

import com.rodvar.esports.R;
import com.rodvar.esports.presentation.BaseAdapter;
import com.rodvar.esports.presentation.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rodrigo on 26/11/16.
 *
 */
class SportsListAdapter extends BaseAdapter {

    SportsListAdapter(BasePresenter presenter) {
        super(presenter);
    }

    @Override
    protected BaseAdapter.ViewHolder instantiateViewHolder(View v) {
        return new ViewHolder(v);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.adapter_sports_list;
    }

    /**
     *
     */
    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.title)
        public TextView title;

        ViewHolder(View rootView) {
            super(rootView);
        }

        @OnClick(R.id.title)
        void onClick(View view) {
            getPresenter().onItemClick(this.getPosition());
        }
    }
}
