package com.example.android.architecture.blueprints.todoapp.test.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf

class DrawerScreen : BaseScreen() {

    /*
    ELEMENTS
     */

    private val settingsOption = allOf(
            withId(R.id.design_menu_item_text),
            withText(R.string.settings_title),
            isCompletelyDisplayed()

    )

    /*
    ACTIONS
     */
    fun clickOnSettingsOption() {
        onView(settingsOption).perform(click())
    }
}