package com.cy.devtool

import android.app.Application
import com.cy.devtool.lifecycle.DevActivityLifecycle

object DevManager {
    fun init(application: Application) {
        registerLifecycle(application)
    }

    private fun registerLifecycle(application: Application) {
        application.registerActivityLifecycleCallbacks(DevActivityLifecycle())
    }

}