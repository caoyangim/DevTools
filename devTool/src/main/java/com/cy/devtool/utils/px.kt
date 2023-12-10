package com.cy.devtool.utils

import android.content.res.Resources.getSystem

/**
 * px2dp.
 */
val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

val Float.px: Float get() = this * getSystem().displayMetrics.density