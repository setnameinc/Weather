package com.setname.weather.mvp.utils.adapters

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.view.View

class Selector(context: Context) : View(context) {

    var paint: Paint = Paint()
    var length: Float = 0f

    init {

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        length = (height+width)*2f

        val animator = ObjectAnimator.ofFloat(this, "phase", 1f, 0f)
        animator.duration = 10000
        animator.start()
    }

    fun setPhase(phase: Float) {
        paint.pathEffect = createPathEffect(length, phase, 0.0f)
        invalidate()//will call onDraw
    }

    private fun createPathEffect(pathLength: Float, phase: Float, offset: Float): PathEffect {
        return DashPathEffect(
            floatArrayOf(pathLength, pathLength),
            Math.max(phase * pathLength, offset)
        )
    }

    override fun onDraw(canvas: Canvas) {

        /*val path = Path()

        path.addRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 10f, 10f, Path.Direction.CCW)

        canvas.drawPath(path, paint)*/

        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 10f, 10f, paint)

    }

}