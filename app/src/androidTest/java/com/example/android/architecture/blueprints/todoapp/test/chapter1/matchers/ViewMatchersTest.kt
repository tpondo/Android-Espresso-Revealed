package com.example.android.architecture.blueprints.todoapp.test.chapter1.matchers

import android.support.design.widget.FloatingActionButton
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.LayoutMatchers
import android.support.test.espresso.matcher.PreferenceMatchers
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Lists all ViewMatchers. ViewMatchers here are without functional load.
 * This is done for demonstration purposes.
 */
@RunWith(AndroidJUnit4::class)
class ViewMatchersTest {
    @Test
    fun userProperties() {
        Espresso.onView(ViewMatchers.withId(R.id.fab_add_task))
        Espresso.onView(ViewMatchers.withText("All TO-DOs"))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.menu_filter))
        Espresso.onView(ViewMatchers.hasContentDescription())
        Espresso.onView(ViewMatchers.withHint(R.string.name_hint))
    }

    @Test
    fun uiProperties() {
        Espresso.onView(ViewMatchers.isDisplayed())
        Espresso.onView(ViewMatchers.isEnabled())
        Espresso.onView(ViewMatchers.isChecked())
        Espresso.onView(ViewMatchers.isSelected())
    }

    @Test
    fun objectMatcher() {
        Espresso.onView(CoreMatchers.not(ViewMatchers.isChecked()))
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withText("item 1"), ViewMatchers.isChecked()))
    }

    @Test
    fun hierarchy() {
        Espresso.onView(ViewMatchers.withParent(ViewMatchers.withId(R.id.todo_item)))
        Espresso.onView(ViewMatchers.withChild(ViewMatchers.withText("item 2")))
        Espresso.onView(ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.todo_item)))
        Espresso.onView(ViewMatchers.hasDescendant(ViewMatchers.isChecked()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isFocusable()))
        Espresso.onView(ViewMatchers.hasSibling(ViewMatchers.withContentDescription(R.string.menu_filter)))
    }

    @Test
    fun input() {
        Espresso.onView(ViewMatchers.supportsInputMethods())
        Espresso.onView(ViewMatchers.hasImeAction(EditorInfo.IME_ACTION_SEND))
    }

    @Test
    fun classMatchers() {
        Espresso.onView(ViewMatchers.isAssignableFrom(CheckBox::class.java))
        Espresso.onView(ViewMatchers.withClassName(CoreMatchers.`is`(FloatingActionButton::class.java.canonicalName)))
    }

    @Test
    fun rootMatchers() {
        Espresso.onView(ViewMatchers.isFocusable())
        Espresso.onView(ViewMatchers.withText(R.string.name_hint)).inRoot(RootMatchers.isTouchable())
        Espresso.onView(ViewMatchers.withText(R.string.name_hint)).inRoot(RootMatchers.isDialog())
        Espresso.onView(ViewMatchers.withText(R.string.name_hint)).inRoot(RootMatchers.isPlatformPopup())
    }

    @Test
    fun preferenceMatchers() {
        Espresso.onData(PreferenceMatchers.withSummaryText("3 days"))
        Espresso.onData(PreferenceMatchers.withTitle(R.string.pref_title_send_notifications))
        Espresso.onData(PreferenceMatchers.withKey("example_switch"))
        Espresso.onView(ViewMatchers.isEnabled())
    }

    @Test
    fun layoutMatchers() {
        Espresso.onView(LayoutMatchers.hasEllipsizedText())
        Espresso.onView(LayoutMatchers.hasMultilineText())
    }
}