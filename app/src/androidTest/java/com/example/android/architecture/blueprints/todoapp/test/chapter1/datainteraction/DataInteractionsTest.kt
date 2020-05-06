package com.example.android.architecture.blueprints.todoapp.test.chapter1.datainteraction

import android.preference.PreferenceActivity
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.PreferenceMatchers
import android.support.test.espresso.matcher.ViewMatchers
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.test.BaseTest
import com.example.android.architecture.blueprints.todoapp.test.helpers.CommonElements.openDrawer
import org.hamcrest.CoreMatchers
import org.junit.Test

/**
 * DataInteraction samples.
 */
class DataInteractionsTest : BaseTest() {
    @Test
    fun dataInteractionSample() {
        openDrawer()
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.design_menu_item_text),
                ViewMatchers.withText(R.string.settings_title))).perform(ViewActions.click())

        // Start of the flow as shown on Figure 1-8.
        Espresso.onData(CoreMatchers.instanceOf(PreferenceActivity.Header::class.java))
                .inAdapterView(ViewMatchers.withId(android.R.id.list))
                .atPosition(0)
                .onChildView(ViewMatchers.withId(android.R.id.title))
                .check(ViewAssertions.matches(ViewMatchers.withText("General")))
                .perform(ViewActions.click())
        Espresso.onData(PreferenceMatchers.withKey("email_edit_text")) /*we have to point explicitly what is the parent of of the General prefs list
                because there are two {@ListView}s with the same id - android.R.id.list in the hierarchy*/
                .inAdapterView(CoreMatchers.allOf(ViewMatchers.withId(android.R.id.list), ViewMatchers.withParent(ViewMatchers.withId(android.R.id.list_container))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.edit)).perform(ViewActions.replaceText("sample@ema.il"))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())

        // Verify new email is shown.
        Espresso.onData(PreferenceMatchers.withKey("email_edit_text"))
                .inAdapterView(CoreMatchers.allOf(ViewMatchers.withId(android.R.id.list), ViewMatchers.withParent(ViewMatchers.withId(android.R.id.list_container))))
                .onChildView(ViewMatchers.withId(android.R.id.summary))
                .check(ViewAssertions.matches(ViewMatchers.withText("sample@ema.il")))
    }
}