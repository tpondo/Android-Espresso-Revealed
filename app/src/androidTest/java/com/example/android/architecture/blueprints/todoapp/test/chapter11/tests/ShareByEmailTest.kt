package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.GeneralScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.*
import org.junit.Assert.assertTrue

class ShareByEmailTest {
    private val generalScreen = GeneralScreen()
    private val settingsScreen = SettingsScreen()

    /*
    Starting from Settings activity , just to make test shorter and avoid
    navigation from todos list
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun cancelsEmailToShareDialog() {

        settingsScreen
                .clickOnGeneralOption()
                .clickOnShareByEmailOption()

        generalScreen.tapCancelOnShareByEmailDialog()

        assertTrue("Previous screen with share by email option should be displayed after pressing Cancel on dialog view"
                , generalScreen.isShareByEmailOptionExist())
    }
}