package com.example.android.architecture.blueprints.todoapp.test.steps.addtodo

import androidx.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import cucumber.api.java.After
import cucumber.api.java.Before

class BaseSteps {

    var rule = ActivityTestRule(TasksActivity::class.java, false, false)
    @Before
    fun launchActivity() {
        rule.launchActivity(null)
    }
    @After
    fun finishActivity() {
        rule.finishActivity()
    }
}