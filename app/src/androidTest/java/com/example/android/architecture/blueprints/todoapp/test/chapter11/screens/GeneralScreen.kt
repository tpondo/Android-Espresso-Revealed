package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf

class GeneralScreen : BaseScreen() {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

    /*
    ELEMENTS
     */
    private val shareByEmailOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_email),
            isCompletelyDisplayed()
    )

    /*
    ACTIONS
     */
    fun clickOnShareByEmailOption(): GeneralScreen {
        onView(shareByEmailOption).perform(click())
        return this
    }

    fun tapCancelOnShareByEmailDialog() {
        uiDevice.findObject(By.res("android:id/button2")).click()
    }

    /*
    HELPERS
     */
    fun isShareByEmailOptionDisplayed(): Boolean {
        return viewExists(shareByEmailOption)
    }
}