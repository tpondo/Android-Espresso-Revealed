package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import com.example.android.architecture.blueprints.todoapp.R


class NotificationsScreen : BaseScreen() {

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
    private val notificationSwitch = allOf(
            withId(android.R.id.switch_widget),
            withParent(hasSibling(withChild(withText(R.string.pref_title_new_message_notifications)))),
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
            onView(notificationSwitch).check(matches(isChecked()))
            true
        } catch (e: Throwable) {
            false
        }
    }

    fun verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications(): Boolean {
        return viewExists(notifyWhenToDoOlderThanOption) && viewExists(ringtoneOption) && viewExists(vibrateOption)
    }
}