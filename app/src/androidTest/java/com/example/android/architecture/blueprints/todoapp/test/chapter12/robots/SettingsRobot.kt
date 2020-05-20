package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf

fun settings(func: SettingsRobot.() -> Unit) = SettingsRobot().apply { func() }
class SettingsRobot {

    fun checkDefaultLayout() {
        onView(allOf(withId(android.R.id.title), withText(R.string.pref_header_general))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(android.R.id.title), withText(R.string.pref_header_notifications))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(android.R.id.title), withText(R.string.pref_header_data_sync))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(android.R.id.title), withText(R.string.pref_header_webview_sample))).check(matches(isCompletelyDisplayed()))
    }
   infix fun clickOnGeneralOption(func: GeneralRobot.() -> Unit): GeneralRobot {
        onView(allOf(withId(android.R.id.title), withText(R.string.pref_header_general)))
                .perform(click())
       return GeneralRobot().apply { func() }
    }

}