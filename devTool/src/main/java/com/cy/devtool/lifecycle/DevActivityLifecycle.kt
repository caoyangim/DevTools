package com.cy.devtool.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.cy.devtool.core.DevViewHelper

class DevActivityLifecycle : Application.ActivityLifecycleCallbacks {
    private val mActivityStackList = mutableListOf<Activity>()
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        mActivityStackList.add(activity)
        DevViewHelper.get(activity).onCreated()
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivityStackList.remove(activity)
        DevViewHelper.get(activity).onDestroyed()
    }
}