package com.example.android.architecture.blueprints.todoapp.test.steps.addtodo

import android.util.Log
import androidx.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.test.screens.ToDoListScreen
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert.assertTrue

class AddTodoSteps {

    private val toDoListScreen = ToDoListScreen()

    var rule = ActivityTestRule(TasksActivity::class.java,false,false)

    @Before
    @Throws(Exception::class)
    fun launchActivity() {
        rule.launchActivity(null)
    }

    @After
    @Throws(java.lang.Exception::class)
    fun finishActivity() {
        rule.finishActivity()
    }

    @Given("I can see todo list")
    fun i_can_see_todo_list() {
        assertTrue("Todo list is not displayed",toDoListScreen.isTodoListDisplayed())
    }

    @When("I click add button")
    fun i_click_add_button() {
        toDoListScreen.clickOnAddButton()
    }

    @And("I enter todo title")
    fun test_3() {
        Log.e("cucu", "then")
    }

    @And("I click description field")
    fun test_4() {
        Log.e("cucu", "then")
    }

    @And("I enter description")
    fun test_5() {
        Log.e("cucu", "then")
    }

    @And("I click done button")
    fun test_6() {
        Log.e("cucu", "then")
    }

    @Then("I expect to see successfully added todo on the list")
    fun asd() {

    }
}