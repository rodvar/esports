package com.rodvar.esports.presentation.sportsfeed;

import android.view.View;
import android.widget.TextView;

import com.rodvar.esports.R;
import com.rodvar.esports.presentation.BaseAdapter;
import com.rodvar.esports.presentation.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rodrigo on 30/11/16.
 */
class SportsFeedAdapter extends BaseAdapter {

    /**
     * @param presenter
     */
    protected SportsFeedAdapter(BasePresenter presenter) {
        super(presenter);
    }

    @Override
    protected BaseAdapter.ViewHolder instantiateViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.adapter_feed_list;
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
