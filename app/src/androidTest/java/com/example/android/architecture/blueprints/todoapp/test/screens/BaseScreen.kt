package com.example.android.architecture.blueprints.todoapp.test.screens

import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.SettingsScreen
import com.example.android.architecture.blueprints.todoapp.test.chapter11.screens.StatisticsScreen
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf.allOf

/**
 * Base screen that shares common functionality for main application settings
 * like TO-DO list screen and Statistics screen.
 */
open class BaseScreen {

    private val hamburgerButton = allOf(
            instanceOf(ImageButton::class.java),
            withParent(withId(R.id.toolbar)),
            isCompletelyDisplayed()
    )

    fun openMenu(): MenuDrawerView {
        onView(hamburgerButton).perform(click())
        return MenuDrawerView()
    }

    inner class MenuDrawerView {
        private val todoListMenuItem = allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.list_title),
                isCompletelyDisplayed()
        )
        private val statisticsMenuItem = allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.statistics_title),
                isCompletelyDisplayed()
        )
        private val settingsMenuItem = allOf(
                withId(R.id.design_menu_item_text),
                withText(R.string.settings_title),
                isCompletelyDisplayed()
        )
        private val todoMenuLogo = allOf(withId(R.id.headerTodoLogo), isCompletelyDisplayed())
        private val todoMenuText = allOf(withId(R.id.headerTodoText), isCompletelyDisplayed())

        fun clickTodoListMenuItem() {
            onView(todoListMenuItem).perform(click())
        }

        fun clickStatisticsMenuItem() {
            onView(statisticsMenuItem).perform(click())
        }

        fun clickSettingsMenuItem() {
            onView(settingsMenuItem).perform(click())
        }

        fun verifyMenuLayout(): MenuDrawerView {
            onView(todoMenuText).check(matches(allOf(
                    withText(R.string.navigation_view_header_title),
                    isCompletelyDisplayed()
            )))

            onView(statisticsMenuItem).check(matches(isDisplayed()))
            onView(todoListMenuItem).check(matches(isDisplayed()))
            return this
        }
    }
}
