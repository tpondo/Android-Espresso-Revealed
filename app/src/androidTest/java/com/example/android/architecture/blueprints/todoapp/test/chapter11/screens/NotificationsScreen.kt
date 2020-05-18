package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.R
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.PreferenceMatchers.withKey
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf


class NotificationsScreen : BaseScreen() {

    /*
    ELEMENTS
     */
    private val enableNotificationsOption = allOf(
            withId(R.id.title),
            withText("Enable notifications"),
            isCompletelyDisplayed()
    )
    private val notifyWhenToDoOlderThanOption = allOf(
            withId(R.id.title),
            withText("Notify when TO-DO older than"),
            isCompletelyDisplayed()
    )
    private val ringtoneOption = allOf(
            withId(R.id.title),
            withText("Ringtone"),
            isCompletelyDisplayed()
    )
    private val vibrateOption = allOf(
            withId(R.id.title),
            withText("Vibrate"),
            isCompletelyDisplayed()
    )


    /*
    ACTIONS
     */
    fun setNotificationsSwitch(statement: Boolean): NotificationsScreen {
        val currentState = isNotificationSwitchEnabled()
        if (!currentState && statement) {
            onView(enableNotificationsOption).perform(click())
        } else if (currentState && !statement) {
            onView(enableNotificationsOption).perform(click())
        }
        return this
    }

    /*
    HELPERS
     */
    private fun isNotificationSwitchEnabled(): Boolean {
        return try {
            onData(withKey("notifications_new_message"))
                    .inAdapterView(allOf(withId(R.id.list), withParent(withId(R.id.list_container))))
                    .onChildView(allOf(withId(R.id.switch_widget), withParent(hasSibling(withChild(withText("Enable notifications"))))))
                    .check(matches(isChecked()))
            true
        } catch (e: Throwable) {
            false
        }
    }

    fun verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications(): Boolean {
        return viewExists(notifyWhenToDoOlderThanOption) && viewExists(ringtoneOption) && viewExists(vibrateOption)
    }
}