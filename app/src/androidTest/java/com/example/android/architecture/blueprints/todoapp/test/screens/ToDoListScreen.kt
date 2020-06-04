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
    private val fabAddButton = allOf(withId(R.id.fab_add_task), isCompletelyDisplayed())
    private val toolbarMenuFilter = allOf(withId(R.id.menu_filter), isCompletelyDisplayed())
    private val todoList = allOf(withId(R.id.tasks_list), isCompletelyDisplayed())
    private val todoSavedSnackbar = allOf(withText(R.string.successfully_saved_task_message), isCompletelyDisplayed())
    private val youHaveNoToDosText = allOf(withText(R.string.no_tasks_all), isCompletelyDisplayed())
    private val taskMarkedCompleteSnackbar = allOf(withText(R.string.task_marked_complete), isCompletelyDisplayed())
    private val snackbar = allOf(withText(R.id.snackbar_text), isCompletelyDisplayed())
    private val screenTitle = allOf(withText(R.string.app_name), withParent(withId(R.id.toolbar)))

    private val allFilterItem = allOf(withId(R.id.title), withText(R.string.nav_all))
    private val completedFilterItem = allOf(withId(R.id.title), withText(R.string.nav_completed))
    private val activeFilterItem = allOf(withId(R.id.title), withText(R.string.nav_active))

    private val clearCompletedMenuItem = allOf(withId(R.id.title), withText(R.string.menu_clear))
    private val refreshMenuItem = allOf(withId(R.id.title), withText(R.string.refresh))

    /*
    ACTIONS
     */
    fun openTaskDetails(taskTitle: String): ToDoDetailsScreen {
        onView(todoList).perform(actionOnHolderItem<RecyclerView.ViewHolder>(withTitle(taskTitle), click()))
        return ToDoDetailsScreen()
    }

    fun openFilter(): ToDoListScreen {
        onView(toolbarMenuFilter).perform(click())
        return this
    }

    fun clickAddFabButton() {
        ConditionWatchers.waitForElementIsGone(todoSavedSnackbar)
        ConditionWatchers.waitForElementIsGone(taskMarkedCompleteSnackbar)
        onView(fabAddButton).perform(click())
    }

    fun verifyToDoIsDisplayed(taskItem: TodoItem?): ToDoListScreen {
        ConditionWatchers.waitForElementIsGone(todoSavedSnackbar)
        onView(todoList).perform(scrollToHolder<RecyclerView.ViewHolder>(withTitle(taskItem!!.title)))
        return this
    }

    fun verifyToDoItemNotShown(taskItem: TodoItem?): ToDoListScreen {
        onView(todoList).perform(verifyTaskNotInTheList(taskItem))
        return this
    }

    fun verifyToDoListScreenInitialState(): ToDoListScreen {
        onView(screenTitle).check(matches(isDisplayed()))
        onView(youHaveNoToDosText).check(matches(isDisplayed()))
        onView(fabAddButton).check(matches(isDisplayed()))
        onView(toolbarMenuFilter).check(matches(isDisplayed()))
        return this
    }

    fun showAllTasks(): ToDoListScreen {
        onView(toolbarMenuFilter).perform(click())
        onView(allFilterItem).perform(click())
        return this
    }

    fun showActiveTasks(): ToDoListScreen {
        onView(toolbarMenuFilter).perform(click())
        onView(activeFilterItem).perform(click())
        return this
    }

    fun completeTask(taskItem: TodoItem?): ToDoListScreen {
        onView(todoList).perform(clickTodoCheckBoxWithTitle(taskItem!!.title))
        return this
    }

    fun showCompletedTasks(): ToDoListScreen {
        onView(toolbarMenuFilter).perform(click())
        onView(completedFilterItem).perform(click())
        return this
    }

    fun clearCompletedTasks(): ToDoListScreen {
        openContextualActionModeOverflowMenu()
        onView(clearCompletedMenuItem).perform(click())
        return this
    }

    fun refreshTasksList(): ToDoListScreen {
        openContextualActionModeOverflowMenu()
        onView(refreshMenuItem).perform(click())
        return this
    }

    fun shareTaskList(): ToDoListScreen {
        openContextualActionModeOverflowMenu()
        onView(clearCompletedMenuItem).perform(click())
        return this
    }
}
