package com.example.android.architecture.blueprints.todoapp.test.chapter12.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.R

/**
 * Builder pattern applied to Add/Edit TO-DO list screens.
 */
class BuilderAddEditToDoRobot {

    fun title(title: String): BuilderAddEditToDoRobot {
        onView(withId(R.id.add_task_title))
                .perform(typeText(title), closeSoftKeyboard())
        return this
    }

    fun description(description: String): BuilderAddEditToDoRobot {
        onView(withId(R.id.add_task_description))
                .perform(typeText(description), closeSoftKeyboard())
        return this
    }

    fun done() {
        onView(withId(R.id.fab_edit_task_done)).perform(click())
    }
}
