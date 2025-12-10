package com.example.labcalculator;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class MainActivityCalculatorUiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addTwoNumbers_displaysCorrectResult() {
        // Press 1
        onView(withId(R.id.btn1)).perform(click());

        // Press +
        onView(withId(R.id.btnAdd)).perform(click());

        // Press 2
        onView(withId(R.id.btn2)).perform(click());

        // Press =
        onView(withId(R.id.btnEquals)).perform(click());

        // Check that display contains "3"
        onView(withId(R.id.txtDisplay))
                .check(matches(withText(containsString("3"))));
    }
}
