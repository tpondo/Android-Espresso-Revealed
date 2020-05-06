package com.example.android.architecture.blueprints.todoapp.test.chapter1.actions

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.chapter1.data.TestData
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

/**
 * Demonstrates ViewActions usage.
 */
class ViewActionsTest : BaseTest() {
    private var toDoTitle = ""
    private var toDoDescription = ""

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        toDoTitle = TestData.toDoTitle
        toDoDescription = TestData.toDoDescription
    }

    @Test
    fun addsNewToDo() {
        // Add new TO-DO.
        Espresso.onView(ViewMatchers.withId(R.id.fab_add_task)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_title))
                .perform(ViewActions.typeText(toDoTitle), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_description))
                .perform(ViewActions.typeText(toDoDescription), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.fab_edit_task_done)).perform(ViewActions.click())

        // Verify new TO-DO with title is shown in the TO-DO list.
        Espresso.onView(ViewMatchers.withText(toDoTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checksToDoStateChange() {
        // Add new TO-DO.
        Espresso.onView(ViewMatchers.withId(R.id.fab_add_task)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_title))
                .perform(ViewActions.typeText(toDoTitle), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_description))
                .perform(ViewActions.typeText(toDoDescription), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.fab_edit_task_done)).perform(ViewActions.click())

        // Mark TO-DO as completed.
        Espresso.onView(ViewMatchers.withId(R.id.todo_complete)).perform(ViewActions.click())

        // Filter out completed TO-DO.
        Espresso.onView(ViewMatchers.withId(R.id.menu_filter)).perform(ViewActions.click())
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.title), ViewMatchers.withText("Active"))).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.todo_title)).check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.menu_filter)).perform(ViewActions.click())
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.title), ViewMatchers.withText("Completed"))).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.todo_title))
                .check(ViewAssertions.matches(CoreMatchers.allOf(ViewMatchers.withText(toDoTitle), ViewMatchers.isDisplayed())))
    }

    @Test
    fun editsToDo() {
        val editedToDoTitle = "Edited $toDoTitle"
        val editedToDoDescription = "Edited $toDoDescription"

        // Add new TO-DO.
        Espresso.onView(ViewMatchers.withId(R.id.fab_add_task)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_title))
                .perform(ViewActions.typeText(toDoTitle), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_description))
                .perform(ViewActions.typeText(toDoDescription), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.fab_edit_task_done)).perform(ViewActions.click())

        // Edit TO-DO.
        Espresso.onView(ViewMatchers.withText(toDoTitle)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fab_edit_task)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_title))
                .perform(ViewActions.replaceText(editedToDoTitle), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.add_task_description))
                .perform(ViewActions.replaceText(editedToDoDescription), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.fab_edit_task_done)).perform(ViewActions.click())

        // Verify edited TO-DO is shown.
        Espresso.onView(ViewMatchers.withText(editedToDoTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}