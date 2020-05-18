package com.example.android.architecture.blueprints.todoapp.test.chapter11.tests

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObject
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.UiWatcher
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.GeneralScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter8.UiAutomatorUiWatcherTest
import org.junit.*

class ShareByEmailTest {
    private val generalScreen = GeneralScreen()
    private val settingsScreen = SettingsScreen()
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

    /*
    Starting from Settings activity , just to make test shorter and avoid
    navigation from todos list
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Before
    // Register dialog watcher.
    fun before() = registerShareByEmailDialogWatcher()

    @After
    fun after() = removeShareByEmailDialogWatcher()

    @Test
    fun cancelsEmailToShareDialog() {

        settingsScreen
                .clickOnGeneralOption()
                .clickOnShareByEmailOption()

        uiDevice.findObject(UiSelector().text("Email to share TO-DO list"))

    }

    private fun registerShareByEmailDialogWatcher() {
        uiDevice.registerWatcher("ShareByEmailDialogWatcher", shareByEmailDialogWatcher)

        // Run registered watcher.
        uiDevice.runWatchers()
    }

    private fun removeShareByEmailDialogWatcher() {
        uiDevice.removeWatcher("ShareByEmailDialogWatcher")
    }

    companion object {
        private val instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

        val shareByEmailDialogWatcher = UiWatcher {
            val cancelDialogButton = uiDevice.findObject(By.res("android:id/button2"))
            if (null != cancelDialogButton) {
                cancelDialogButton.click()
                return@UiWatcher true
            }
            false
        }
    }

}