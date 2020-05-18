package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.preference.PreferenceActivity
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.AppCompatImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.CoreMatchers.*

/**
 * Represents application Settings screen.
 */
class SettingsScreen {
    /*
    ELEMENTS
     */
    private val generalOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_header_general)
    )

    private val upButton = onView(allOf(
            instanceOf(AppCompatImageButton::class.java),
            withParent(withId(R.id.action_bar))))

    /*
    ACTIONS
     */
    fun clickOnGeneralOption(): GeneralScreen {
        onData(anything())
                .inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .onChildView(generalOption)
                .click()
        return GeneralScreen()
    }

    fun navigateUpToToDoListScreen(): ToDoListScreen {
        upButton.perform(click())
        return ToDoListScreen()
    }

    fun navigateUpToStatisticsScreen(): StatisticsScreen {
        upButton.perform(click())
        return StatisticsScreen()
    }
}
