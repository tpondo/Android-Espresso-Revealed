package com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers

import com.example.android.architecture.blueprints.todoapp.R.id.*
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import com.example.android.architecture.blueprints.todoapp.test.chapter3.viewWithId
import com.example.android.architecture.blueprints.todoapp.test.chapter3.viewWithText
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElement
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers.waitForElementIsGone
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test


/**
 * ConditionWatcher test sample.
 */
class ConditionWatcherTest : BaseTest() {

    private val addTaskFab = viewWithId(fab_add_task)
    private val drawerMenu = onView(allOf(
            withId(R.id.nav_view),
            hasDescendant(withText("Settings"))
    ))

    private val settingsOption = viewWithText("Settings")


    @Test
    fun waitForElementCondition() {
        waitForElement(addTaskFab, 400).click()
    }

    @Test
    fun navigateToSettingsFromTodoList() {
        openDrawer()

        waitForElement(drawerMenu, 400)

        settingsOption.click()

        waitForElementIsGone(drawerMenu, 400)
    }
}
