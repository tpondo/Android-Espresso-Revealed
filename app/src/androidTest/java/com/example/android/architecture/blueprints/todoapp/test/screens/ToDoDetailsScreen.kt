package com.example.android.architecture.blueprints.todoapp.test.screens

import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import com.example.android.architecture.blueprints.todoapp.test.helpers.Utils.getStringFromTestResource
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf.allOf

/**
 * Represents the TO-DO item Details screen.
 */
class ToDoDetailsScreen {

    /*
    ELEMENTS
     */
    private val taskMarkedCompleteText = getStringFromTestResource(com.example.android.architecture.blueprints.todoapp.test.R.string.task_marked_complete)
    private val taskMarkedActiveText = getStringFromTestResource(com.example.android.architecture.blueprints.todoapp.test.R.string.task_marked_active)
    private val taskDescription = allOf(withId(R.id.task_detail_description), isCompletelyDisplayed())
    private val taskTitle = allOf(withId(R.id.task_detail_title), isCompletelyDisplayed())
    private val completeTaskCheckbox = allOf(withId(R.id.task_detail_complete), isCompletelyDisplayed())
    private val fabEditTaskButton = allOf(withId(R.id.fab_edit_task), isCompletelyDisplayed())
    private val deleteTaskToolbarButton = allOf(withId(R.id.menu_delete), isCompletelyDisplayed())
    private val completeSnackbar = allOf(withText(taskMarkedCompleteText), isCompletelyDisplayed())
    private val activeSnackbar = allOf(withText(taskMarkedActiveText), isCompletelyDisplayed())
    private val hamburgerButton = allOf(instanceOf<Any>(ImageButton::class.java), withParent(withId(R.id.toolbar)), isCompletelyDisplayed())

    fun tapCheckbox(): ToDoDetailsScreen {
        onView(completeTaskCheckbox).perform(click())
        return this
    }

    fun clickEditToDoFabButton() {
        onView(fabEditTaskButton).perform(click())
    }

    fun deleteTask() {
        onView(deleteTaskToolbarButton).perform(click())
    }

    fun verifyTaskDetails(taskItem: TodoItem): ToDoDetailsScreen {
        onView(taskTitle).check(matches(withText(taskItem.title)))
        onView(taskDescription).check(matches(withText(taskItem.description)))
        return this
    }

    fun verifyCompleteSnackbarShown(): ToDoDetailsScreen {
        onView(completeSnackbar).check(matches(isDisplayed()))
        return this
    }

    fun verifyActiveSnackbarShown(): ToDoDetailsScreen {
        onView(activeSnackbar).check(matches(isDisplayed()))
        return this
    }

    fun returnToTaskList() {
        onView(hamburgerButton).perform(click())
    }
}
