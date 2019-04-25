package com.setname.weather.mvp.utils.animation.rain

import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import com.setname.weather.R
import kotlinx.android.synthetic.main.rain.view.*
import kotlinx.coroutines.*
import kotlin.random.Random


/*

This is a demo view

 */

class RainAnimationFragment : Fragment() {

    private val screenHeight = 1920
    private val screenWidth = 1080

    private lateinit var viewRainAnimationFragment: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewRainAnimationFragment = inflater.inflate(R.layout.rain, container, false)

        viewRainAnimationFragment.alpha = 0.25f

        val rainAnimation = RainAnimation(container!!.context)
        rainAnimation.rotation = 25f

        viewRainAnimationFragment.apply {

            rain_test_const_layout.addView(rainAnimation)

        }

        return viewRainAnimationFragment

    }

    inner class RainAnimation(context: Context) : FrameLayout(context) {

        init {

            val countOfLines = 40

            val areaDistribution = (screenHeight / countOfLines)

            val correctValForRotationFromHeightToWeigh = (screenHeight - screenWidth) / 2

            CoroutineScope(Dispatchers.Main).launch {

                withContext(Dispatchers.Main) {

                    for (i in 0..countOfLines) {

                        val startXForGenerationRandomX = i * areaDistribution - correctValForRotationFromHeightToWeigh
                        val endXForGenerationRandomX =
                            (i + 1) * areaDistribution - correctValForRotationFromHeightToWeigh

                        val random =
                            Random.nextInt(
                                startXForGenerationRandomX,
                                endXForGenerationRandomX
                            ).toFloat()

                        Log.i("RainAF", "random = ${random}")

                        val drop = Drop(context, random)
                        addView(drop)
                        delay(500)

                    }

                }

            }


        }

    }

    inner class Drop(context: Context, val fromX: Float) : View(context) {

        val paint = Paint()
        var path: Path = Path()

        val lineSeparatorLength = Random.nextInt(7, 10) * 100f
        val lineLength = 20f

        init {

            paint.style = Paint.Style.STROKE
            paint.color = Color.BLACK
            paint.strokeWidth = 3f

            drawSeparatedLine()
            setAnimation()

        }

        private fun setAnimation() {

            drawSeparatedLine()

            val animation =
                TranslateAnimation(fromX, fromX, -screenHeight.toFloat(), screenHeight + lineLength)

            animation.apply {

                duration = 4000L
                repeatMode = RESTART
                repeatCount = INFINITE
                interpolator = LinearInterpolator()

            }

            this.startAnimation(animation)

        }

        private fun drawSeparatedLine() {

            path.moveTo(0f, 0f)
            path.lineTo(0f, screenHeight.toFloat() + lineSeparatorLength + lineLength)
            path.close()

            paint.pathEffect = DashPathEffect(floatArrayOf(lineLength, lineSeparatorLength), 0f)


        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            canvas?.drawPath(path, paint)

        }
    }

}