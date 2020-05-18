package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.NotificationsScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EnableNotificationsTest {
    private val settingsScreen = SettingsScreen()
    private val notificationsScreen = NotificationsScreen()

    /*
   Starting from Settings activity , just to make test shorter and avoid
   navigation from todos list
    */
    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun enablesNotificationsAndVerifiesIfAdditionalOptionsAreVisible() {
        settingsScreen
                .clickOnNotificationsOption()
                .setNotificationsSwitch(true)

        assertTrue("After enabling notifications 3 additional options should be visible : todos older than , ringtone and vibrate"
                , notificationsScreen.verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications())

    }

    @Test
    fun disablesNotificationsAndVerifiesIfAdditionalOptionsAreHidden() {
        settingsScreen
                .clickOnNotificationsOption()
                .setNotificationsSwitch(false)

        assertFalse("After disabling notifications 3 additional options should be hidden : todos older than , ringtone and vibrate"
                , notificationsScreen.verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications())

    }
}