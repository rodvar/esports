package com.rodvar.esports;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.rodvar.esports.di.Injector;
import com.rodvar.esports.presentation.AppFragment;

/**
 * App Main Activity. Provides fragments container and navigation.
 */
public class MainActivity extends Activity implements AppFragment.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AppFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
//        this.getActionBar().setDisplayHomeAsUpEnabled(false);
        this.loadFragment(R.id.fragment_container
                , Injector.getInstance().instantiateSportListFragment(this), false);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    /**
     *
     * @param url
     */
    public void navigateFeed(String url) {
        this.loadFragment(R.id.fragment_container
                , Injector.getInstance().instantiateFeedFragment(this, url), true);
    }

    /**
     *  @param containerId
     * @param fragment
     * @param addToBackStack
     */
    private void loadFragment(int containerId, AppFragment fragment, boolean addToBackStack) {
        try {
            FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction()
                    .replace(containerId, (Fragment) fragment, fragment.getClass().getSimpleName());
            if (addToBackStack)
                fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            this.currentFragment = fragment;
            this.updateSubTitle();
        } catch (Exception e) {
            Log.e(TAG, "Failed to load fragment", e);
        }
    }

    private void updateSubTitle() {
        this.getActionBar().setSubtitle(this.currentFragment.getTitleResId());
    }
}
