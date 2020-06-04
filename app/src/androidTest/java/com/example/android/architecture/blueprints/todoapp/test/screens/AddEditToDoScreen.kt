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
    private val addToDoDescriptionEditText = allOf(
            withId(R.id.add_task_description),
            isCompletelyDisplayed()
    )
    private val addToDoTitleEditText = allOf(
            withId(R.id.add_task_title),
            isCompletelyDisplayed()
    )
    private val doneFabButton = allOf(
            withId(R.id.fab_edit_task_done),
            isCompletelyDisplayed()
    )
    private val emptyToDoSnackbar = allOf(
            withText(R.string.empty_task_message),
            isCompletelyDisplayed()
    )
    private val upButton = allOf(
            instanceOf(ImageButton::class.java),
            withParent(withId(R.id.toolbar)),
            isCompletelyDisplayed()
    )
    /*
    ACTIONS
     */
    fun typeToDoTitle(title: String): AddEditToDoScreen {
        onView(addToDoTitleEditText).perform(typeText(title), closeSoftKeyboard())
        return this
    }

    fun typeToDoDescription(taskDescription: String): AddEditToDoScreen {
        onView(addToDoDescriptionEditText).perform(typeText(taskDescription), closeSoftKeyboard())
        return this
    }

    fun updateToDoTitle(title: String): AddEditToDoScreen {
        onView(addToDoTitleEditText).perform(clearText(), typeText(title), closeSoftKeyboard())
        return this
    }

    fun updateToDoDescription(taskDescription: String): AddEditToDoScreen {
        onView(addToDoDescriptionEditText).perform(clearText(), typeText(taskDescription), closeSoftKeyboard())
        return this
    }

    /**
     * Add new TO-DO flow
     */
    fun addNewToDo(taskItem: TodoItem?): ToDoListScreen {
        typeToDoTitle(taskItem!!.title)
        typeToDoDescription(taskItem.description)
        clickDoneFabButton()
        return ToDoListScreen()
    }

    /**
     * Edit existing TO-DO flow
     */
    fun updateToDo(taskItem: TodoItem?): ToDoListScreen {
        updateToDoTitle(taskItem!!.title)
        updateToDoDescription(taskItem.description)
        clickDoneFabButton()
        return ToDoListScreen()
    }

    fun addEmptyToDo(): AddEditToDoScreen {
        clickDoneFabButton()
        return this
    }

    fun clickDoneFabButton() {
        onView(doneFabButton).perform(click())
    }

    fun clickUpButton() {
        onView(upButton).perform(click())
    }

    fun clickBackButton() {
        Espresso.pressBack()
    }

    fun verifySnackbarForEmptyToDo(): AddEditToDoScreen {
        onView(emptyToDoSnackbar).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        return this
    }
}
