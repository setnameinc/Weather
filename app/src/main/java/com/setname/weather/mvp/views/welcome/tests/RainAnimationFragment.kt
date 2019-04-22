package com.setname.weather.mvp.views.welcome.tests

import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import com.setname.weather.R
import kotlinx.android.synthetic.main.rain_test.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/*

This is a demo view

 */

class RainAnimationFragment : Fragment() {

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

        return viewRainAnimationFragment

    }

    inner class RainAnimation(context: Context) : FrameLayout(context) {

        private val poolSize = 8

        private val imageList: MutableList<ImageView> = mutableListOf()

        private val dropListVertical: MutableList<Drop> = mutableListOf()
        private val dropListHorizontal: MutableList<Drop> = mutableListOf()

        init {

            val LISTS_SIZE = 20

            for (i in 0..LISTS_SIZE - 1) {

                dropListVertical.add(Drop(context))
                dropListVertical[i].redraw(-30.toDouble())
                addView(dropListVertical[i])
                /*delay(Random.nextInt(500, 1000).toLong())*/

            }

            for (i in 0..LISTS_SIZE - 1) {

                dropListHorizontal.add(Drop(context))
                dropListHorizontal[i].visibility = View.INVISIBLE
                dropListHorizontal[i].redraw(-30.toDouble())
                addView(dropListHorizontal[i])

                /*delay(Random.nextInt(500, 1000).toLong())*/

            }

            var countOfVertical: Int = 0
            var lastPos: Int = 0

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    CoroutineScope(Dispatchers.Main).launch {

                        withContext(Dispatchers.Main) {

                            countOfVertical = (seekBar!!.progress - 90) * LISTS_SIZE / 90

                            Log.i("RainAF", "countOfVertical = ${countOfVertical}")
                            Log.i("RainAF", "angle = ${seekBar!!.progress - 90}")

                            if (countOfVertical > 0) {

                                for (i in lastPos..lastPos + Math.abs(countOfVertical)) {

                                    dropListVertical[i].visibility = View.GONE
                                    dropListHorizontal[i].visibility = View.VISIBLE

                                    if (lastPos + countOfVertical - 1 == i) {

                                        lastPos = countOfVertical

                                    }

                                }

                            } else {

                                for (i in lastPos + Math.abs(countOfVertical)..lastPos) {

                                    dropListVertical[i].visibility = View.VISIBLE
                                    dropListHorizontal[i].visibility = View.GONE

                                    if (lastPos + countOfVertical - 1 == i) {

                                        lastPos = countOfVertical

                                    }

                                }

                            }

                            for (i in dropListVertical) {

                                i.redraw((seekBar!!.progress - 90).toDouble())

                                /*delay(Random.nextInt(500, 1000).toLong())*/

                            }

                            for (i in dropListHorizontal){

                                i.redraw((seekBar!!.progress - 90).toDouble())

                            }

                        }

                    }

                }

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            })

        }

    }

    inner class Drop(context: Context) : View(context) {

        var angle = -30.toDouble()
        var fromX = 0f
        var toX = 0f

        val customHeight = 1920f
        val customWidth = 1080f

        var curX = 0f

        val paint = Paint()
        lateinit var path: Path

        init {

            paint.style = Paint.Style.STROKE
            paint.color = Color.BLACK
            paint.strokeWidth = 3f

        }

        private fun setAnimation() {
            fromX = Random.nextInt(1350).toFloat()
            toX = fromX + customHeight * Math.tan(Math.toRadians(angle)).toFloat()

            val animation =
                TranslateAnimation(fromX, toX, -1920f, 1920f)
            animation.apply {

                duration = 5000L
                repeatMode = RESTART
                repeatCount = INFINITE
                interpolator = LinearInterpolator()

            }
            this.startAnimation(animation)
        }

        fun redraw(curAngle: Double) {

            angle = curAngle
            path = Path()

            drawSeparatedLine()
            setAnimation()


            invalidate()

        }

        private fun drawSeparatedLine() {

            val dropLength = 20

            val fromXLocal = 100f
            val dX = dropLength * Math.sin(Math.toRadians(angle))
            val toXLocal = fromXLocal + dX

            val dY = Math.sqrt(dropLength * dropLength - dX * dX)

            path.moveTo(fromXLocal, 0f)
            path.lineTo(toXLocal.toFloat(), 0 + dY.toFloat())
            path.moveTo(fromXLocal, 400f)
            path.lineTo(toXLocal.toFloat(), 400f + dY.toFloat())
            path.moveTo(fromXLocal, 800f)
            path.lineTo(toXLocal.toFloat(), 800f + dY.toFloat())
            path.moveTo(fromXLocal, 1200f)
            path.lineTo(toXLocal.toFloat(), 1200f + dY.toFloat())
            path.moveTo(fromXLocal, 1600f)
            path.lineTo(toXLocal.toFloat(), 1600f + dY.toFloat())

        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            canvas?.drawPath(path, paint)

        }
    }

}