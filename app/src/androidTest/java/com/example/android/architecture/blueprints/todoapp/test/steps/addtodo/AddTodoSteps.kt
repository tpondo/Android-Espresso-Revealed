package com.example.android.architecture.blueprints.todoapp.test.steps.addtodo

import android.util.Log
import androidx.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class AddTodoSteps {

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
    fun test_1() {
        Log.e("cucu", "given")
    }

    @When("I click on fab add button")
    fun test_2() {
        Log.e("cucu", "when")
    }

    @Then("I can see todo editor view")
    fun test_3() {
        Log.e("cucu", "then")
    }
}