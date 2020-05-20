package com.example.android.architecture.blueprints.todoapp.test.chapter12

import android.support.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.settings.SettingsActivity
import com.example.android.architecture.blueprints.todoapp.test.chapter12.robots.BaseRobotTest
import com.example.android.architecture.blueprints.todoapp.test.chapter12.robots.settings
import org.junit.Rule
import org.junit.Test

class GeneralRobotTest : BaseRobotTest()  {

    /*
 Starting from Settings activity , just to make test shorter and avoid
 navigation from todos list
  */
    @get:Rule
    var activityTestRule = ActivityTestRule(SettingsActivity::class.java)

    @Test
    fun cancelsShareByEmailDialog() {
        settings {
            clickOnGeneralOption {
                clickOnShareByEmailOption()
                cancelShareByEmailDialog()
            }
        }
    }
}