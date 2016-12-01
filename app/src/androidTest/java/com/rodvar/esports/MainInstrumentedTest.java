package com.rodvar.esports;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rodvar.esports.presentation.MainListFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class);
    // Context of the app under test.
    private Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void useAppContext() throws Exception {
        assertEquals("com.rodvar.esports", appContext.getPackageName());
    }

    @Test
    public void mainActivityStartsOnMailListFragment() {
        assertEquals(MainListFragment.class, this.activityTestRule.getActivity().getCurrentFragment().getClass());
    }

    @Test
    public void title() {
        assertEquals(appContext.getString(R.string.app_name)
                , this.activityTestRule.getActivity().getActionBar().getTitle());
        assertEquals(appContext.getString(R.string.title_sports_list)
                , this.appContext.getString(this.activityTestRule.getActivity()
                        .getCurrentFragment().getTitleResId()));
    }

    @Test
    public void feedViewChangeTitle() {
        onView(withId(R.id.recycler_view))
                .perform(click());
        assertEquals(appContext.getString(R.string.title_sport_feed)
                , this.appContext.getString(this.activityTestRule.getActivity()
                        .getCurrentFragment().getTitleResId()));
    }


}
