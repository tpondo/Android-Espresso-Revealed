package com.example.android.architecture.blueprints.todoapp.test.screens

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything

class SettingsScreen : BaseScreen() {

    /*
  ELEMENTS
   */
    private val notificationsOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_header_notifications),
            isCompletelyDisplayed()
    )

    /*
    ACTIONS
     */

    fun clickOnNotificationsOption() {
        onData(anything())
                .inAdapterView(withId(android.R.id.list))
                .atPosition(1)
                .onChildView(notificationsOption)
                .click()
    }
}