package com.example.android.architecture.blueprints.todoapp.test.screens

import android.widget.ImageButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

/**
 * Represents both Create new TO-DO and Edit existing TO-DO screens.
 */
class AddEditToDoScreen {

    /*
    ELEMENTS
     */
    private val addToDoDescriptionInput = allOf(
            withId(R.id.add_task_description),
            isCompletelyDisplayed()
    )
    private val addToDoTitleInput = allOf(
            withId(R.id.add_task_title),
            isCompletelyDisplayed()
    )
    private val doneFabButton = allOf(
            withId(R.id.fab_edit_task_done),
            isCompletelyDisplayed()
    )

    /*
    ACTIONS
     */
    fun typeToDoTitle(title: String): AddEditToDoScreen {
        onView(addToDoTitleInput).perform(typeText(title), closeSoftKeyboard())
        return this
    }

    fun typeToDoDescription(taskDescription: String): AddEditToDoScreen {
        onView(addToDoDescriptionInput).perform(typeText(taskDescription), closeSoftKeyboard())
        return this
    }


    fun clickDoneFabButton() {
        onView(doneFabButton).perform(click())
    }

}
