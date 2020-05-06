package com.example.android.architecture.blueprints.todoapp.test.chapter2.customfailurehandler;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.test.BaseTest;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CustomFailureHandlerTestClass extends BaseTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void addsNewToDo() {
        // Add new TO-DO.
        onView(withId(R.id.fab_add_task)).perform(click());
        onView(withId(R.id.add_task_title))
                .perform(typeText("toDoTitle"), closeSoftKeyboard());
        onView(withId(R.id.add_task_description))
                .perform(typeText("toDoDescription"), closeSoftKeyboard());
        onView(withId(R.id.fab_edit_task_done)).perform(click());

        // Verify new TO-DO with title is shown in the TO-DO list.
        onView(withText("toDoTitles")).check(matches(isDisplayed()));
    }
}
