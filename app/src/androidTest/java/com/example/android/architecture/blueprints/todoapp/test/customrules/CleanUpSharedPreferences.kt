package com.example.android.architecture.blueprints.todoapp.test.customrules

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.v7.preference.PreferenceManager
import com.example.android.architecture.blueprints.todoapp.R
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CleanUpSharedPreferences : TestRule {
    override fun apply(base: Statement, description: Description) = MyStatement(base)

    class MyStatement(private val base: Statement) : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            //before
            val targetContext = getInstrumentation().targetContext
            val preferencesEditor = PreferenceManager.getDefaultSharedPreferences(targetContext).edit()

            preferencesEditor.clear()
            preferencesEditor.commit()
            try {
                base.evaluate() // This executes your tests
            } finally {
                // after
            }
        }
    }
}