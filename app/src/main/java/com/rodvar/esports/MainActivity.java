package com.rodvar.esports;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rodvar.esports.di.Injector;
import com.rodvar.esports.presentation.AppFragment;

/**
 * App Main Activity. Provides fragments container and navigation.
 */
public class MainActivity extends AppCompatActivity implements AppFragment.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AppFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadFragment(R.id.fragment_container
                , Injector.getInstance().instantiateSportListFragment(this));
    }

    /**
     *
     * @param containerId
     * @param fragment
     */
    private void loadFragment(int containerId, AppFragment fragment) {
        try {
            this.getFragmentManager().beginTransaction()
                    .replace(containerId, (Fragment) fragment)
                    .commit();
            this.currentFragment = fragment;
        } catch (Exception e) {
            Log.e(TAG, "Failed to load fragment", e);
        }
    }
}
