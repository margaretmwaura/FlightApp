package com.android.flightapp;


import android.content.Context;
import android.content.Intent;

import com.android.flightapp.Model.Flight;
import com.android.flightapp.View.AirportActivity;
import com.android.flightapp.View.FlightScheduleActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
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
public class FlightScheduleDisplayTest
{
    private CountingIdlingResource countingIdlingResource;

    @Rule
    public ActivityTestRule<FlightScheduleActivity> mActivityTestRule
            = new ActivityTestRule<FlightScheduleActivity>(FlightScheduleActivity.class,true,true) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, FlightScheduleActivity.class);
            result.putExtra("FirstAirportCode", "DAR");
            result.putExtra("SecondAirportCode", "FNC");

            return result;
        }
    };

    @Before
    public void registerIdlingResource()
    {
        countingIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }


    @Test
    public void recycler_view_schedule(){
        onView(withId(R.id.flight_recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.flight_recyclerView))
                .check(matches(atPosition(2, hasDescendant(withText("FlightSchedule")))));

    }


    @After
    public void unregisterIdlingResource() {
        if (countingIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }
}
