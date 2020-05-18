package com.example.android.architecture.blueprints.todoapp.test.chapter11.screens

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter3.click
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything

class GeneralScreen : BaseScreen() {

    /*
    ELEMENTS
     */
    private val shareByEmailOption = allOf(
            withId(android.R.id.title),
            withText(R.string.pref_title_email),
            isCompletelyDisplayed()
    )
//    val shareByEmailUiObject: UiObject = uiDevice.findObject(UiSelector()
//            .text("Email to share TO-DO list"))

    /*
    ACTIONS
     */
    fun clickOnShareByEmailOption(): GeneralScreen {
        onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list), withParent(withId(android.R.id.list_container))))
                .atPosition(0)
                .onChildView(hasDescendant(shareByEmailOption))
                .click()
        return this
    }

    /*
    HELPERS
     */


}