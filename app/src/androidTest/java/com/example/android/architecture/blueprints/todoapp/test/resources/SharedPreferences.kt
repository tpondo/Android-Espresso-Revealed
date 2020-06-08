package com.example.android.architecture.blueprints.todoapp.test.resources

import androidx.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation

object SharedPreferences {

    fun cleanSharedPreferences() {
        val targetContext = getInstrumentation().targetContext
        val preferencesEditor = PreferenceManager.getDefaultSharedPreferences(targetContext).edit()

        preferencesEditor.clear()
        preferencesEditor.commit()
    }
}