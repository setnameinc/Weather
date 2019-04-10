package com.setname.weather.mvp.views.welcome.tests

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_weather_per_three_hours_model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Logger
import android.graphics.PathDashPathEffect
import android.R.attr.start
import android.animation.ObjectAnimator
import android.graphics.DashPathEffect
import android.graphics.PathEffect
import java.lang.reflect.Array.getLength
import android.R.attr.path
import android.graphics.PathMeasure










class SelectorTest : Fragment() {

    private lateinit var viewSelectorTest: View

    private val log by lazy {

        Logger.getLogger("SelectorTest")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var clickerCount = 0

        viewSelectorTest =
            inflater.inflate(com.setname.weather.R.layout.adapter_weather_per_three_hours_model, container, false)


        //
        CoroutineScope(Dispatchers.Main).launch {

            val drawView = DrawView(context!!)
            val drawViewLayoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            drawView.layoutParams = drawViewLayoutParams
            val constraintLayout = adapter_weather_per_three_hours_model_const_layout

            withContext(Dispatchers.Main) {
                viewSelectorTest.setOnClickListener {

                    if (clickerCount % 2 == 0) {

                        constraintLayout.addView(drawView)

                    } else {

                        constraintLayout.removeView(drawView)


                    }

                    clickerCount++

                }
            }

        }

        return viewSelectorTest
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    internal inner class DrawView(context: Context) : View(context) {

        lateinit var paint:Paint
        lateinit var path:Path
        var length: Float = 0f

        init {

            paint = Paint()
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 5f

            path = makeRect(240f, 348f)

            val measure = PathMeasure(path, false)
            length = measure.length

            val intervals = floatArrayOf(length, length)

            val animator = ObjectAnimator.ofFloat(this, "phase", 1.0f, 0.0f)
            animator.duration = 3000
            animator.start()

        }

        fun setPhase(phase: Float) {
            paint.pathEffect = createPathEffect(length, phase, 0.0f)
            invalidate()//will calll onDraw
        }

        private fun createPathEffect(pathLength: Float, phase: Float, offset: Float): PathEffect {
            return DashPathEffect(
                floatArrayOf(pathLength, pathLength),
                Math.max(phase * pathLength, offset)
            )
        }

        override fun onDraw(canvas: Canvas) {

            val path = makeRect(240f, 348f)

            canvas.drawPath(path, paint)


        }

        private fun makeRect(length: Float, height: Float): Path {
            val p = Path()
            p.moveTo(0f, 0f)
            p.lineTo(length, 0f)
            p.moveTo(length, 0f)
            p.lineTo(length, height)
            p.moveTo(length, height)
            p.lineTo(0f, height)
            p.moveTo(0f, height)
            p.lineTo(0f, 0f)
            p.close()
            return p
        }

    }

}