package com.setname.weather.mvp.utils.adapters

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.view.View

class Selector(context: Context) : View(context) {

    var paint: Paint = Paint()
    var length: Float = (height.toFloat() + width.toFloat())

    init {

        paint.color = Color.parseColor("#BDBDBD")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1f

        /*val animator = ObjectAnimator.ofFloat(this, "phase", 1f, 0f)
        animator.duration = 5000
        animator.start()*/
    }

    /*fun setPhase(phase: Float) {
        paint.pathEffect = createPathEffect(length, phase, 0.0f)
        invalidate()//will call onDraw
    }

    private fun createPathEffect(pathLength: Float, phase: Float, offset: Float): PathEffect {
        return DashPathEffect(
            floatArrayOf(pathLength, pathLength),
            Math.max(phase * pathLength, offset)
        )
    }

    private val path = Path()*/

    override fun onDraw(canvas: Canvas) {

        /*path.addRoundRect(
            paint.strokeWidth / 2,
            paint.strokeWidth / 2,
            width.toFloat() - paint.strokeWidth,
            height.toFloat() - paint.strokeWidth,
            10f,
            10f,
            Path.Direction.CCW
        )

        canvas.drawPath(path, paint)*/

        canvas.drawRoundRect(
            paint.strokeWidth+5,
            paint.strokeWidth,
            width.toFloat() - paint.strokeWidth-5,
            height.toFloat() - paint.strokeWidth,
            50f,
            50f,
            paint
        )

    }

}