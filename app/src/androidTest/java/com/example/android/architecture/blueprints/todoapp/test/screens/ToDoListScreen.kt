package com.example.android.architecture.blueprints.todoapp.test.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter11.testdata.TodoItem
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomRecyclerViewActions.ClickTodoCheckBoxWithTitleViewAction.clickTodoCheckBoxWithTitle
import com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions.CustomViewActions.verifyTaskNotInTheList
import com.example.android.architecture.blueprints.todoapp.test.chapter2.custommatchers.RecyclerViewMatchers.withTitle
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers
import org.hamcrest.core.AllOf.allOf

/**
 * Represents TO-DO list screen.
 */
class ToDoListScreen : BaseScreen() {

    /*
    ELEMENTS
     */
    private val tasksContainer = withId(R.id.tasksContainer)
    private val toolbar = withId(R.id.toolbar)
    private val todoHeader = allOf(
            isDescendantOfA(toolbar),
            withText(R.string.app_name),
            isCompletelyDisplayed()
    )
    private val youHaveNoTodosInformation = allOf(
            isDescendantOfA(withId(R.id.noTasks)),
            withText(R.string.no_tasks_all),
            isCompletelyDisplayed()
    )

    /*
    ACTIONS
     */
    fun clickOnAddButton() {
        val addButton = allOf(
                isDescendantOfA(tasksContainer),
                withId(R.id.fab_add_task),
                isCompletelyDisplayed()
        )
        onView(addButton).perform(click())
    }

    /*
    HELPERS
     */
    fun isTodoListDisplayed(): Boolean {
       return viewExists(todoHeader) && viewExists(youHaveNoTodosInformation)
    }
}
