package com.vvechirko.darkthemetest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class Chart1(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    private var dy = 0f
    private var dx = 0f

    private val values = IntArray(20)

    init {
        val t = context.obtainStyledAttributes(intArrayOf(R.attr.chartColor))
        val lineColor = t.getColor(0, Color.BLACK)
        t.recycle()

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.color = lineColor

        for (i in values.indices) {
            values[i] = Random.nextInt(0, 60)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        dx = width / (values.size - 1f)
        dy = height / 60f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var x = 0f
        var y = height - values[0] * dy
        path.moveTo(x, y)
        for (i in 1 until values.size) {
            x += dx
            y = height - values[i] * dy
            path.lineTo(x, y)
        }
        canvas.drawPath(path, paint)
    }
}