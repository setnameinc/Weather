package com.setname.weather.mvp.views.welcome.tests

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
import android.widget.SeekBar
import com.setname.weather.R
import kotlinx.android.synthetic.main.rain_test.view.*
import kotlinx.coroutines.*
import kotlin.random.Random

/*

This is a demo view

 */

class RainAnimationFragment : Fragment() {

    private val screenHeight = 1920
    private val screenWidth = 1080

    private lateinit var viewRainAnimationFragment: View

    private lateinit var seekBar: SeekBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewRainAnimationFragment = inflater.inflate(R.layout.rain_test, container, false)

        /*viewRainAnimationFragment.alpha = 0.3f*/

        viewRainAnimationFragment.apply {

            seekBar = rain_test_seek_bar

        }

        val rainAnimation = RainAnimation(container!!.context)

        viewRainAnimationFragment.apply {

            rain_test_const_layout.addView(rainAnimation)

        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                rainAnimation.rotation = (progress - 90).toFloat()

            }

        })

        return viewRainAnimationFragment

    }

    inner class RainAnimation(context: Context) : FrameLayout(context) {

        init {

            val countOfLines = 20

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


                        val drop =
                            Drop(context, random)
                        addView(drop)
                        delay(Random.nextInt(500, 1000).toLong())

                    }

                }

            }


        }

    }

    inner class Drop(context: Context, val fromX: Float) : View(context) {

        val paint = Paint()
        var path: Path = Path()

        val lineSeparatorLength = /*Random.nextInt(4, 6)*/ 5 * 100.toFloat()
        val lineLength = 30f

        init {

            paint.style = Paint.Style.STROKE
            paint.color = Color.BLACK
            paint.strokeWidth = 3f

            drawSeparatedLine()
            setAnimation()

        }

        private fun setAnimation() {

            drawSeparatedLine()

            //TODO(probably 1 think for bug)
            val sumOfOneLine = (lineSeparatorLength + lineLength) / 2

            //TODO(probably 2 think for bug)
            val animation =
                TranslateAnimation(fromX, fromX, -sumOfOneLine, sumOfOneLine)

            animation.apply {

                duration = 1000L
                repeatMode = RESTART
                repeatCount = INFINITE
                interpolator = LinearInterpolator()

            }

            this.startAnimation(animation)

        }


        private fun drawSeparatedLine() {

            path.moveTo(0f, 0f)
            path.lineTo(0f, screenHeight.toFloat() + lineSeparatorLength + lineLength)

            paint.pathEffect = DashPathEffect(floatArrayOf(lineLength, lineSeparatorLength), 0f)


        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            canvas?.drawPath(path, paint)

        }
    }

}