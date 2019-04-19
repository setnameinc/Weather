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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Logger
import android.animation.ObjectAnimator
import android.graphics.DashPathEffect
import android.graphics.PathEffect
import com.setname.weather.mvp.utils.adapters.Selector
import com.setname.weather.mvp.utils.poor.AppContext
import kotlinx.android.synthetic.main.selector_test.*


class SelectorTest : Fragment() {

    private lateinit var viewSelectorTest: View

    private val log by lazy {

        Logger.getLogger("SelectorTest")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var clickerCount = 0

        viewSelectorTest =
            inflater.inflate(com.setname.weather.R.layout.selector_test, container, false)

        CoroutineScope(Dispatchers.Main).launch {

            var drawViewDraw:Selector? = null
            val constraintLayout = selector_test_constraint_layout

            withContext(Dispatchers.Main) {
                viewSelectorTest.setOnClickListener {

                    if (clickerCount % 2 == 0) {

                        drawViewDraw = Selector(AppContext.applicationContext())
                        val drawViewLayoutParams =
                            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        drawViewDraw?.layoutParams = drawViewLayoutParams

                        constraintLayout.addView(drawViewDraw)

                    } else {

                        constraintLayout.removeView(drawViewDraw)


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

        var paint:Paint = Paint()
        var length: Float = 0f

        init {

            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 5f

            length = 2600f

            val animator = ObjectAnimator.ofFloat(this, "phase", 1f, 0f)
            animator.duration = 3000
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

        private val path = Path()

        override fun onDraw(canvas: Canvas) {

            path.addRoundRect(0f, 0f, 500f, 800f, 10f, 10f, Path.Direction.CCW)

            canvas.drawPath(path, paint)


        }

    }

}