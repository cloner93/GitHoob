package com.milad.githoob.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Dot(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val COLOR_HEX = "#E74300"
    private var paint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val x = width
        val y = height
        val radius = 100
        paint.style = Paint.Style.FILL;
        canvas.drawPaint(paint);
        paint.color = Color.parseColor(COLOR_HEX);

        canvas.drawCircle((x / 3).toFloat(), (y / 3).toFloat(), radius.toFloat(), paint)
    }
}