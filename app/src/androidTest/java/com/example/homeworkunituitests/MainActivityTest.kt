package com.example.homeworkunituitests

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import com.example.dependencyinjections.R

class MainActivityTest {

    @Rule
    fun mainActivityRule() = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun when_launch_shows_text_press_button() {
        Espresso.onView(ViewMatchers.withId(R.id.result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Press button")))
    }

    @Test
    fun when_press_button_text_changes_to_processing() {
        Espresso.onView(ViewMatchers.withId(R.id.button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.result))
            .check(ViewAssertions.matches(ViewMatchers.withText("Processing…")))
    }
}
