package com.example.android.architecture.blueprints.todoapp.test.steps.enablesnotifications

import android.app.Activity
import android.util.Log
import androidx.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements
import com.example.android.architecture.blueprints.todoapp.test.resources.SharedPreferences
import com.example.android.architecture.blueprints.todoapp.test.screens.DrawerScreen
import com.example.android.architecture.blueprints.todoapp.test.screens.NotificationsScreen
import com.example.android.architecture.blueprints.todoapp.test.screens.SettingsScreen
import com.example.android.architecture.blueprints.todoapp.test.screens.ToDoListScreen
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert

class EnablesNotificationsSteps {

    private val toDoListScreen = ToDoListScreen()
    private val settingsScreen = SettingsScreen()
    private val drawerScreen = DrawerScreen()
    private val notificationsScreen = NotificationsScreen()

    var rule = ActivityTestRule(TasksActivity::class.java, false, false)

    @Before
    fun setup() {
        Log.e("before", "enable notif steps")
    }

    @Given("I can see list of todos")
    fun i_can_see_list_of_todos() {
        rule.launchActivity(null)

        Assert.assertTrue("Todo list is not displayed", toDoListScreen.isTodoListDisplayed())
    }

    @When("I click hamburger menu")
    fun i_click_hamburger_menu() {
        CommonElements.openDrawer()
    }

    @And("I select settings option")
    fun i_select_settings_option() {
        drawerScreen.clickOnSettingsOption()
    }

    @And("I select notifications option")
    fun i_select_notifications_option() {
        settingsScreen.clickOnNotificationsOption()
    }

    @And("I click on enable notifications option")
    fun i_click_on_enable_notifications_option() {
        notificationsScreen.clickOnEnableNotificationsOption()
    }

    @Then("I expect to see 3 additional options enabled")
    fun i_expect_to_see_3_additional_options_enabled() {
        notificationsScreen.verifiesIfAdditionalOptionsAreVisibleAfterEnablingNotifications()
    }

}