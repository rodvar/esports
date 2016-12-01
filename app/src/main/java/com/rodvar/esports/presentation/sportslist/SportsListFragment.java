package com.rodvar.esports.presentation.sportslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rodvar.esports.R;
import com.rodvar.esports.presentation.BaseFragment;
import com.rodvar.esports.presentation.BasePresenter;

import butterknife.BindView;

/**
 * Created by rodrigo on 26/11/16.
 * Fragment which only purpose is providing a recycler view to present that as the main content.
 */
public class SportsListFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SportsListFragment() {

    }

    public static SportsListFragment instantiate(Listener activity
            , BasePresenter presenter, int titleResId) {
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
        this.adapter = new SportsListAdapter((BasePresenter) this.getPresenter());
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
