package com.example.android.architecture.blueprints.todoapp.test.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf


class NotificationsScreen : BaseScreen(){

    /*
       ELEMENTS
        */
    private val enableNotificationsOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_new_message_notifications),
            isCompletelyDisplayed()
    )
    private val notifyWhenToDoOlderThanOption = allOf(
            withId(android.R.id.title),
            withText(R.string.slider_title),
            isCompletelyDisplayed()
    )
    private val ringtoneOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_ringtone),
            isCompletelyDisplayed()
    )
    private val vibrateOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_vibrate),
            isCompletelyDisplayed()
    )

    /*
    ACTIONS
     */

    fun clickOnEnableNotificationsOption() {
        onView(enableNotificationsOption).perform(click())
    }

    /*
    HELPERS
     */
    fun verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications(): Boolean {
        return viewExists(notifyWhenToDoOlderThanOption) && viewExists(ringtoneOption) && viewExists(vibrateOption)
    }

}