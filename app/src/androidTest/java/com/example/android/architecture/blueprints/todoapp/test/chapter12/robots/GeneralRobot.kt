package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf

fun general(func: GeneralRobot.() -> Unit) = GeneralRobot().apply { func() }
class GeneralRobot {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

    fun clickOnShareByEmailOption() {
        onView(allOf(withId(android.R.id.title),withText(R.string.pref_title_email))).perform(click())
    }

    fun cancelShareByEmailDialog(){
        uiDevice.findObject(By.res("android:id/button2")).click()
    }

}