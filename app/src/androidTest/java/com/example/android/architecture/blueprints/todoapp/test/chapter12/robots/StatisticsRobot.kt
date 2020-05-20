package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf

fun statistics(func: StatisticsRobot.() -> Unit) = StatisticsRobot().apply { func() }
class StatisticsRobot {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

    fun cancelStatisticsAlertDialog() {
        uiDevice.findObject(By.res("android:id/button2")).click()
    }

    fun verifyIfNoAvailableStatisticsInformationIsDisplayed() {
        onView(allOf(withId(R.id.statistics), withText(R.string.statistics_no_tasks)))
                .check(matches(isCompletelyDisplayed()))
    }
}