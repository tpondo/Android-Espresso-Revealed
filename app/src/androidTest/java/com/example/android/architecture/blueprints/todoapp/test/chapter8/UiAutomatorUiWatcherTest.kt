package com.example.android.architecture.blueprints.todoapp.test.chapter8

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.*
import android.support.v7.widget.LinearLayoutCompat
import android.widget.ImageButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Demonstrates [UiWatcher] functionality in test.
 */
@RunWith(AndroidJUnit4::class)
class UiAutomatorUiWatcherTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Before
    // Register dialog watcher.
    fun before() = registerAppShareDialogWatcher()
    //    fun before() = registerStatisticsDialogWatcher()


    @After
    fun after() = removeAppShareDialogWatcher()
//    fun after() = removeStatisticsDialogWatcher()

    @Test
    fun dismissesStatisticsDialogUsingWatcher() {

        val toolbar =
                "com.example.android.architecture.blueprints.todoapp.mock:id/toolbar"
        val menuDrawer =
                "com.example.android.architecture.blueprints.todoapp.mock:id/design_navigation_view"

        // Open menu drawer.
        uiDevice.findObject(
                UiSelector().resourceId(toolbar))
                .getChild(UiSelector().className(ImageButton::class.java.name))
                .click()

        // Open Statistics section.
        uiDevice.findObject(
                UiSelector()
                        .resourceId(menuDrawer)
                        .childSelector(
                                UiSelector()
                                        .className(LinearLayoutCompat::class.java.name).instance(1)))
                .click()

        /**
         * Locate Statistics label based on the view id.
         * At this moment watcher kicks in and dismissed dialog by clicking on OK button.
         */
        val statistics: UiObject = uiDevice.findObject(UiSelector()
                .resourceId("com.example.android.architecture.blueprints.todoapp.mock:id/statistics"))

        // Assert expected text is shown.
        assertTrue("Expected statistics label: \"You have no tasks.\" but got: ${statistics.text}",
                statistics.text == "You have no tasks.")
    }

    @Test
    fun shareByGmailExercise20_3and20_4() {

        val shareMenuItem = onView(allOf(
                withId(R.id.title),
                withText(R.string.share)
        ))

        // open contextual menu in TO-DO list toolbar
        openContextualActionModeOverflowMenu()
        // click on share menu item
        shareMenuItem.perform(click())

        // UiWatcher is suppose to start now
        val gmailWelcomeText: UiObject = uiDevice.findObject(UiSelector()
                .text("Welcome to Gmail"))

        // Assert expected text is shown.
        assertTrue("Welcome to Gmail text should be displayed inside newly opened gmail app , instead got ${gmailWelcomeText}",
                gmailWelcomeText.text == "Welcome to Gmail")

    }

    /**
     * Register Statistics dialog watcher that will monitor dialog presence.
     * Dialog will be dismissed when appeared by clicking on OK button.
     */
    private fun registerStatisticsDialogWatcher() {
        uiDevice.registerWatcher("StatisticsDialogs", statisticsDialogWatcher)

        // Run registered watcher.
        uiDevice.runWatchers()
    }

    private fun registerAppShareDialogWatcher() {
        uiDevice.registerWatcher("StatisticsDialogs", appToShareChooserDialogWatcher)

        // Run registered watcher.
        uiDevice.runWatchers()
    }

    /**
     * Remove previously registered Statistics dialog.
     */
    private fun removeStatisticsDialogWatcher() {
        uiDevice.removeWatcher("StatisticsDialog")
    }

    private fun removeAppShareDialogWatcher() {
        uiDevice.removeWatcher("ApplicationShareDialog")
    }

    companion object {
        private val instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)

        val statisticsDialogWatcher = UiWatcher {
            val okDialogButton = uiDevice.findObject(By.text("OK"))
            if (null != okDialogButton) {
                okDialogButton.click()
                return@UiWatcher true
            }
            false
        }

        val appToShareChooserDialogWatcher = UiWatcher {
            val gmailAppText = uiDevice.findObject(UiSelector().text("Gmail"))
            if (null != gmailAppText) {
                gmailAppText.click()
                return@UiWatcher true
            }
            false
        }
    }
}
