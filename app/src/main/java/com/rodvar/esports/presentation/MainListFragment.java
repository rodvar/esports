package com.rodvar.esports.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rodvar.esports.R;

import butterknife.BindView;

/**
 * Created by rodrigo on 26/11/16.
 * Fragment which only purpose is providing a recycler view to present that as the main content.
 */
public class MainListFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public MainListFragment() {

    }

    public static MainListFragment instantiate(Listener activity
            , BasePresenter presenter, int titleResId) {
        MainListFragment fragment = new MainListFragment();
        fragment.setActivity(activity);
        fragment.setPresenter(presenter);
        fragment.setTitleResId(titleResId);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_sports_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.getPresenter() == null)
            this.getMainActivity().start();
        else {
            this.getPresenter().onViewCreated();
            this.layoutManager = new LinearLayoutManager(this.getActivity());
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setLayoutManager(this.layoutManager);
            this.recyclerView.setAdapter(this.adapter);
            this.recyclerView.setLayoutManager(this.layoutManager);
        }
    }

    @Override
    public void refresh() {
        this.adapter.notifyDataSetChanged();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }
}
