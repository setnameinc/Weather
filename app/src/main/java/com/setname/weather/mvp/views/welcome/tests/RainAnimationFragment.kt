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
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import com.setname.weather.R
import kotlinx.android.synthetic.main.rain_test.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/*

This is a demo view

 */

class RainAnimationFragment : Fragment() {

    private lateinit var viewRainAnimationFragment: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewRainAnimationFragment = inflater.inflate(R.layout.rain_test, container, false)

        viewRainAnimationFragment.alpha = 0.3f

        val rainAnimation = RainAnimation(container!!.context)

        viewRainAnimationFragment.apply {

            rain_test_const_layout.addView(rainAnimation)

        }

        return viewRainAnimationFragment

    }

    inner class RainAnimation(context: Context) : FrameLayout(context) {

        private val poolSize = 8

        private val imageList: MutableList<ImageView> = mutableListOf()

        init {

            /*this.layoutParams = LayoutParams(1080, 1920)

            for (i in 0..poolSize) {

                val imageView = generateDrop()

                addView(imageView, 0)
                imageList.add(imageView)


            }

            CoroutineScope(Dispatchers.Main).launch {

                for (i in imageList) {

                    startAnim(i)
                    *//*delay(5000)*//*

                }

            }*/

            CoroutineScope(Dispatchers.Main).launch {

                for (i in 0..20) {

                    addView(Drop(context))
                    delay(Random.nextInt(500, 1000).toLong())

                }

            }

        }

        private fun generateDrop(): ImageView {

            val image = ImageView(context)
            image.background = ContextCompat.getDrawable(context, R.drawable.drop)
            image.layoutParams = LayoutParams(100, 100)

            return image

        }

        private fun startAnim(image: ImageView) {

            val transAnim =
                TranslateAnimation(Random.nextInt(1000).toFloat(), Random.nextInt(1000).toFloat(), 0f, 1920f)

            transAnim.duration = 3000
            transAnim.setAnimationListener(object : Animation.AnimationListener {

                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {

                    removeView(image)

                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            transAnim.repeatCount = 3

            image.startAnimation(transAnim)

        }


    }

    class Drop(context: Context) : View(context) {

        val angle = -30.toDouble()

        val customHeight = 1920f
        val customWidth = 1080f

        var curX = 0f

        val paint = Paint()
        val path = Path()

        init {

            paint.style = Paint.Style.STROKE
            paint.color = Color.BLACK
            paint.strokeWidth = 3f

            val fromX = Random.nextInt(1350).toFloat()
            val toX = fromX + customHeight * Math.tan(Math.toRadians(angle)).toFloat()

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

        fun drawSeparatedLine() {

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
            drawSeparatedLine()

            canvas?.drawPath(path, paint)

        }
    }

}