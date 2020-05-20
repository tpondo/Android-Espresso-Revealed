package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.ImageButton
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

open class BaseRobotTest {

    companion object Drawer {
        /*
        ELEMENTS
         */
        private val hamburgerButton = allOf(
                instanceOf(ImageButton::class.java),
                withParent(withId(R.id.toolbar)),
                isCompletelyDisplayed()
        )
        private val statisticsOption = allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.statistics_title),
                isCompletelyDisplayed()
        )

        /*
        ACTIONS
         */
        fun openDrawer(): Drawer {
            onView(hamburgerButton).perform(click())
            return this
        }
        fun clickOnStatisticsOption() {
            onView(statisticsOption).perform(click())
        }
    }
}