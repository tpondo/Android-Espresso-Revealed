package com.example.android.architecture.blueprints.todoapp.test.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.resources.NoAction
import org.hamcrest.CoreMatchers.allOf

/**
 * Represents both Create new TO-DO and Edit existing TO-DO screens.
 */
class AddEditToDoScreen : BaseScreen() {

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

    private val emptyTitlePopupText = withText(R.string.add_task_empty_title)


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
        onView(doneFabButton).perform(closeSoftKeyboard(),click())
    }

    fun isEmptyTitlePopupDisplayed(): Boolean {
        return try {
           onView(emptyTitlePopupText).inRoot(isPlatformPopup()).perform(NoAction())
       true
        } catch (e: Throwable) {
            false
        }
    }

}
