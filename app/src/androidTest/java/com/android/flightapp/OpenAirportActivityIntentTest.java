package com.android.flightapp;


import android.app.Activity;
import android.app.Instrumentation;


import com.android.flightapp.View.AirportActivity;
import com.android.flightapp.View.MainActivity;
import com.android.flightapp.View.Splash;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class OpenAirportActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickButton_OpensAirportActivity(){

        onView(withId(R.id.button_mine)).check(matches(isDisplayed()));


        onView(withId(R.id.button_mine)).perform(click());

        intended
                (
                        hasComponent(AirportActivity.class.getName())

                );


    }

}
