package com.android.flightapp;


import com.android.flightapp.View.AirportActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.android.flightapp.TestUtils.atPosition;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AirportActivtyDisplayTest
{

    private CountingIdlingResource countingIdlingResource;

    @Rule
    public ActivityTestRule<AirportActivity> mActivityTestRule
            = new ActivityTestRule<>(AirportActivity.class,true,true);

    @Before
    public void registerIdlingResource()
    {
        countingIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @Test
    public void recycler(){
        onView(withId(R.id.airport_recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.airport_recyclerView))
                .check(matches(atPosition(2, hasDescendant(withText("City Code")))));

    }

    @After
    public void unregisterIdlingResource() {
        if (countingIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }

}
