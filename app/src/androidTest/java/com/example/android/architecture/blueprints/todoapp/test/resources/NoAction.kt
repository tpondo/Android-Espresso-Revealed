package com.example.android.architecture.blueprints.todoapp.test.resources

import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction

import android.view.View
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

class NoAction : ViewAction {
    override fun perform(uiController: UiController?, view: View?) {}

    override fun getConstraints(): Matcher<View> {
        return CoreMatchers.any(View::class.java)
    }

    override fun getDescription(): String {
        return "Dummy action performed."
    }
}