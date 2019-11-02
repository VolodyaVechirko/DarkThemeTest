package com.vvechirko.darkthemetest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class Chart2(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()

    private var dy = 0f
    private var dx = 0f
    private var space = 4f
    private var radius = 4f

    private val values = IntArray(10)

    init {
        val t = context.obtainStyledAttributes(intArrayOf(R.attr.chartColor))
        val lineColor = t.getColor(0, Color.BLACK)
        t.recycle()

        paint.style = Paint.Style.FILL
        paint.color = lineColor

        for (i in values.indices) {
            values[i] = Random.nextInt(0, 60)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val spaceSum = space * (values.size - 1)
        dx = (width - spaceSum) / values.size
        dy = height / 60f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.bottom = height.toFloat()

        for (i in 0 until values.size) {
            rect.left = if (i == 0) 0f else rect.right + space
            rect.right = rect.left + dx
            rect.top = height - values[i] * dy
            canvas.drawRoundRect(rect, radius, radius, paint)
        }
    }
}