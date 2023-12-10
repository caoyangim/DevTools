package com.cy.devtool.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.cy.devtool.R
import com.cy.devtool.utils.DragViewUtil


class DevCircleWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs), DragViewUtil.TouchChangeListener {
    init {
        post {
            setBg()
            changeTranslucentStatus(true)
        }
    }

    private fun setBg() {
        val bgDrawable = GradientDrawable()
        bgDrawable.color = ColorStateList.valueOf(ContextCompat.getColor(context,R.color.dev_circle_color))
        bgDrawable.cornerRadius = minOf(measuredWidth / 2f, measuredHeight / 2f)
        background = bgDrawable
    }

    override fun onTouchChanged(touched: Boolean) {
        changeTranslucentStatus(!touched)
    }

    private var mTranslucentStatus = false

    private val translucentAnimator: ObjectAnimator =
        ObjectAnimator.ofFloat(this, "alpha", 1f, 0.6f)

    private fun changeTranslucentStatus(translucent: Boolean) {
        if (mTranslucentStatus == translucent) {
            return
        }
        mTranslucentStatus = translucent
        if (!translucent) {
            alpha = 1f
            return
        }
        translucentAnimator.apply {
            duration = 500
            start()
        }
    }
}