package com.example.android.architecture.blueprints.todoapp.test.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.chapter4.conditionwatchers.ConditionWatchers
import org.hamcrest.core.AllOf.allOf

/**
 * Represents TO-DO list screen.
 */
class ToDoListScreen : BaseScreen() {

    /*
    ELEMENTS
     */
    private val addButton = allOf(
            withId(R.id.fab_add_task),
            isCompletelyDisplayed()
    )
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

    private val todoSavedSnackbar = allOf(withText(R.string.successfully_saved_task_message), isCompletelyDisplayed())
    /*
    ACTIONS
     */
    fun clickOnAddButton() {
        onView(addButton).perform(click())
    }

    /*
    HELPERS
     */
    fun isTodoListDisplayed(): Boolean {
       return viewExists(todoHeader) && viewExists(youHaveNoTodosInformation)
    }

    /*
    Needs to check if added item is displayed with proper title OR description
    because when item is added without title then description is displayed instead
     */
    fun isAddedTodoDisplayed(todoTitle : String, todoDescription : String): Boolean {
        val addedTodoTitle = allOf(
                withId(R.id.todo_title),
                withText(todoTitle),
                isCompletelyDisplayed()
        )
        val addedTodoDescription = allOf(
                withId(R.id.todo_title),
                withText(todoDescription),
                isCompletelyDisplayed()
        )
        ConditionWatchers.waitForElementIsGone(todoSavedSnackbar)
        return viewExists(addedTodoTitle) || viewExists(addedTodoDescription)
    }
}
