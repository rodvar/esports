package com.rodvar.esports.presentation.sportscontent;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rodvar.esports.R;
import com.rodvar.esports.presentation.BaseFragment;

import butterknife.BindView;

/**
 * Created by rodrigo on 01/12/16.
 */
public class WebContentFragment extends BaseFragment {

    @BindView(R.id.web_view)
    WebView webView;

    private Listener listener;
    private String url;

    public static WebContentFragment instantiate(Listener listener, String url) {
        WebContentFragment fragment = new WebContentFragment();
        fragment.listener = listener;
        fragment.url = url;
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_web_content;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        this.webView.getSettings().setAppCachePath(this.getActivity().getFilesDir().getPath());
        this.webView.getSettings().setAppCacheEnabled(true);
        this.webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.webView.loadUrl(this.url);
    }

    @Override
    public void refresh() {
        // nth
    }
}
