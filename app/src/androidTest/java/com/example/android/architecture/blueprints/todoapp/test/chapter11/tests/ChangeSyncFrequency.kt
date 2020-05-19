package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.DataAndSyncScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChangeSyncFrequency {

    private val settingsScreen = SettingsScreen()
    private val dataAndSyncScreen = DataAndSyncScreen()

    /*
  Starting from Settings activity , just to make test shorter and avoid
  navigation from todos list
   */
    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun changeSyncFrequencyToNever() {
        val frequencySummaryValue = "Never"

        settingsScreen
                .clickOnDataAndSyncOption()
                .clickOnSyncFrequencyOption()
                .selectSyncFrequencyNever()

        assertTrue("Sync summary should be changed to Never"
                , dataAndSyncScreen.isSyncFrequencySummaryDisplayed(frequencySummaryValue))

    }
}