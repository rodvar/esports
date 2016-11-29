package com.rodvar.esports.presentation.sportslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rodvar.esports.R;
import com.rodvar.esports.presentation.BaseFragment;

import butterknife.BindView;

/**
 * Created by rodrigo on 26/11/16.
 * Sports list view responsible for presenting the sports to the user and handle UI interactions
 */
public class SportsListFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SportsListFragment() {

    }

    public static SportsListFragment instantiate(Listener activity
            , SportsListPresenter presenter, int titleResId) {
        SportsListFragment fragment = new SportsListFragment();
        fragment.setActivity(activity);
        fragment.setPresenter(presenter);
        fragment.setTitleResId(titleResId);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.layoutManager = new LinearLayoutManager(this.getActivity());
        this.adapter = new SportsListAdapter((SportsListPresenter) this.getPresenter());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
    }

    @Override
    public int getTitleResId() {
        return R.string.title_sports_list;
    }

    @Override
    public void refresh() {
        this.adapter.notifyDataSetChanged();
    }
}
