package com.example.android.architecture.blueprints.todoapp.test

import android.os.Bundle
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner
import java.io.File

@CucumberOptions(glue = ["com.example.android.architecture.blueprints.todoapp.test.steps"],features = ["features"])
class CucumberTestRunner : CucumberAndroidJUnitRunner() {
    override fun onCreate(bundle: Bundle) {
        bundle.putString("plugin", pluginConfigurationString) // we programmatically create the plugin configuration
        super.onCreate(bundle)
    }

    /**
     * Since we want to checkout the external storage directory programmatically, we create the plugin configuration
     * here, instead of the [CucumberOptions] annotation.
     *
     * @return the plugin string for the configuration, which contains XML, HTML and JSON paths
     */
    private val pluginConfigurationString: String
        private get() {
            val cucumber = "cucumber"
            val separator = "--"
            return "junit:" + absoluteFilesPath + "/" + cucumber + ".xml" + separator +
                    "html:" + absoluteFilesPath + "/" + cucumber + ".html"
        }//sdcard/Android/data/cucumber.cukeulator

    /**
     * The path which is used for the report files.
     *
     * @return the absolute path for the report files
     */
    private val absoluteFilesPath: String
        private get() {

            //sdcard/Android/data/cucumber.cukeulator
            val directory = targetContext.getExternalFilesDir(null)
            return File(directory, "reports").absolutePath
        }
}