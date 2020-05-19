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
        onView(allOf(withId(android.R.id.title), withText("General"))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(android.R.id.title), withText("Notifications"))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(android.R.id.title), withText("Data & sync"))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(android.R.id.title), withText("WebView sample"))).check(matches(isCompletelyDisplayed()))
    }
   infix fun clickOnGeneralOption(func: GeneralRobot.() -> Unit): GeneralRobot {
        onView(allOf(withId(android.R.id.title), withText(R.string.pref_header_general)))
                .perform(click())
       return GeneralRobot().apply { func() }
    }

}