package com.cy.devtool.core

import android.app.Activity
import android.view.ViewGroup
import android.view.Window
import com.cy.devtool.R
import com.cy.devtool.anno.DevelopView
import com.cy.devtool.ui.DevPanelActivity
import com.cy.devtool.utils.DragViewUtil
import com.cy.devtool.widget.DevCircleWidget
import java.util.WeakHashMap

internal class DevViewHelper(private val activity: Activity) {

    companion object {
        private val sActivityCache = WeakHashMap<Activity, DevViewHelper>()
        fun get(activity: Activity): DevViewHelper = sActivityCache.getOrElse(activity) {
            DevViewHelper(activity)
        }
    }

    private lateinit var mDevWidget: DevCircleWidget
    fun onCreated() {
        val annotation = activity::class.java.getAnnotation(DevelopView::class.java)
        if (annotation != null) {
            return
        }
        val decorView = activity.window.decorView
        val contentParent = activity.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
        (decorView as ViewGroup).apply {
            removeView(contentParent)
            activity.layoutInflater.inflate(R.layout.activity_dev_base_layout, this)
        }
        initView()
    }

    private fun initView() {
        mDevWidget =
            activity.findViewById<DevCircleWidget?>(R.id.widget_dev_circle).also { widget ->
                DragViewUtil.registerDragAction(widget)
                widget.setOnClickListener {
                    jump2DevPanel()
                }
                widget.setOnLongClickListener {
                    showAppMessage()
                    true
                }
            }
    }

    private fun jump2DevPanel() {
        DevPanelActivity.start(activity)
    }

    private fun showAppMessage() {

    }

    fun onDestroyed() {}
}