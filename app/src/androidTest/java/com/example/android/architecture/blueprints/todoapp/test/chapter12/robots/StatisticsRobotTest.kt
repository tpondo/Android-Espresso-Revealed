package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

class StatisticsRobotTest : BaseRobotTest() {

    @get:Rule
    var activityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun cancelsStatisticsAlertDialog() {
        openDrawer().clickOnStatisticsOption()

        statistics {
            cancelStatisticsAlertDialog()
            verifyIfNoAvailableStatisticsInformationIsDisplayed()
        }
    }
}