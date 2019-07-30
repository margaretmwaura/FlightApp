package com.android.flightapp;


import android.content.Context;
import android.content.Intent;

import com.android.flightapp.View.FlightScheduleActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class OpenMapActivity {

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
    public void openMapActivity(){
        onView(withId(R.id.flight_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        onView(withId(R.id.map)).check(matches(isDisplayed()));

    }


    @After
    public void unregisterIdlingResource() {
        if (countingIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }
}
