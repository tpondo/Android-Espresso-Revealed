package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf

fun general(func: GeneralRobot.() -> Unit) = GeneralRobot().apply { func() }
class GeneralRobot {

    fun isShareByEmailOptionDisplayed(){
        onView(allOf(withId(android.R.id.title),withText("Email to share TO-DO list"))).check(matches(isCompletelyDisplayed()))
    }
}