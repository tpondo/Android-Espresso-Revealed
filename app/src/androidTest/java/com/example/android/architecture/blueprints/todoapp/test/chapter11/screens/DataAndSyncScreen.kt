package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens


import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.UiSelector
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf

class DataAndSyncScreen : BaseScreen() {
    /*
    ELEMENTS
     */
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

    private val syncFrequencyOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_sync_frequency),
            isCompletelyDisplayed()
    )
    private val syncFrequencyNever = uiDevice.findObject(UiSelector().text("Never"))
    private val syncFrequencySummary = allOf(
            withId(android.R.id.summary),
            hasSibling(syncFrequencyOption),
            isCompletelyDisplayed()
    )

    /*
    ACTIONS
     */
    fun clickOnSyncFrequencyOption(): DataAndSyncScreen {
        onView(syncFrequencyOption).perform(click())
        return this
    }

    fun selectSyncFrequencyNever(): DataAndSyncScreen {
        syncFrequencyNever.click()
        return this
    }

    /*
    HELPERS
     */
    fun isSyncFrequencySummaryDisplayed(summary: String): Boolean {
        val syncFrequencySummaryWithSpecificValue = allOf(syncFrequencySummary, withText(summary))
        return viewExists(syncFrequencySummaryWithSpecificValue)
    }
}