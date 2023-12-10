package com.cy.devtool.ui.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import com.cy.devtool.utils.px
import java.util.Random
import kotlin.math.max
import kotlin.math.min


class CanvasRuleWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var w: Int = 0
    private var h: Int = 0
    private val rectList: MutableList<Rect> = mutableListOf()
    private val paint: Paint = Paint()

    init {
        paint.apply {
            strokeWidth = 10f.px
            color = randomColor()
            style = Paint.Style.STROKE
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // log("onSizeChanged w:${w},h:${h}")
        var index = 0
        while (index < min(w - index, h - index)) {
            val rect = Rect(index, index, w - index, h - index)
            // log("rect:${rect}")
            rectList.add(rect)
            index += 10
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectList.forEach {
            paint.color = randomColor()
            canvas.drawRect(it, paint)
        }
    }

    @ColorInt
    private fun randomColor(alpha: Int = 255): Int {
        val rnd = Random()
        val realAlpha = min(max(1.0, alpha.toDouble()), 255.0).toInt()
        return Color.argb(realAlpha, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    private fun log(msg: String) {
        Log.d("CanvasRuleWidget", msg)
    }
}