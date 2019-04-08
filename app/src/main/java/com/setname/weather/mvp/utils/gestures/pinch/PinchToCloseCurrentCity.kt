package com.setname.weather.mvp.utils.gestures.pinch

import android.content.Context
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration


class PinchToCloseCurrentCity(val viewConfig: ViewConfiguration) {

    private val SCREEN_HEIGTH = 1920f;

    private var ptrCount = 0

    private var primaryStartTouchEventY = -1f
    private var secondStartTouchEventY = -1f

    private var primarySecondStartTouchDistance = 0f
    private var viewScaledTouchSlop = 0

    private var previousDistance = 0f

    private val END_Y = 400f
    private val END_SIZE: Float = 200f
    private val PRIMARY_SECOND_DISTANCE = 200f

    private val UP_ZONE = 100f
    private val DOWN_ZONE = 1820f

    private val INTERACTION_ZONE = DOWN_ZONE - UP_ZONE
    private val INTERACTION_ZONE_FOR_PINCH = DOWN_ZONE - UP_ZONE - PRIMARY_SECOND_DISTANCE

    private val END_SCALE: Float = END_SIZE / SCREEN_HEIGTH

    private var scaleY = 0f
    private var moveY = 0f

    init {
        viewScaledTouchSlop = viewConfig.scaledTouchSlop
    }

    fun onTouch(v: View, event: MotionEvent): Boolean {

        val action = event.action and MotionEvent.ACTION_MASK

        when (action) {

            MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_DOWN -> {

                ptrCount++
                if (ptrCount == 2) {

                    previousDistance = Float.MIN_VALUE
                    secondStartTouchEventY = event.getY(1)
                    primarySecondStartTouchDistance = distance(event, 0, 1)

                    if (primarySecondStartTouchDistance > 0) {

                        scaleY = -(SCREEN_HEIGTH - END_SIZE) / (SCREEN_HEIGTH * INTERACTION_ZONE_FOR_PINCH)
                        moveY = -(SCREEN_HEIGTH / 2 - UP_ZONE - END_Y) / INTERACTION_ZONE_FOR_PINCH

                    } else {

                        scaleY = (SCREEN_HEIGTH - END_SIZE) / (SCREEN_HEIGTH * INTERACTION_ZONE_FOR_PINCH)
                        moveY = (SCREEN_HEIGTH / 2 - UP_ZONE - END_Y) / INTERACTION_ZONE_FOR_PINCH

                    }

                }
            }

            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                ptrCount--
                if (ptrCount < 2) {
                    secondStartTouchEventY = -1f
                    if (v.scaleY > 0.4) {
                        v.animate()
                            .scaleY(1f)
                            .translationY(0f)
                            .start()
                    } else {
                        v.animate()
                            .scaleY(END_SCALE)
                            .translationY(-465f)//TODO(calculating)
                            .start()
                    }
                }
                if (ptrCount < 1) {
                    primaryStartTouchEventY = -1f
                }
            }

            MotionEvent.ACTION_MOVE -> {

                val isPrimMoving = isScrollGesture(event, 0, primaryStartTouchEventY)
                val isSecMoving = ptrCount > 1 && isScrollGesture(event, 1, secondStartTouchEventY)

                if (isSecMoving) {

                    if (isPrimMoving && isSecMoving) {

                        if (Math.abs(primarySecondStartTouchDistance) > INTERACTION_ZONE) {

                            val localDistance = distance(event, 0, 1)

                            if (Math.abs(localDistance) < INTERACTION_ZONE) {

                                if (v.scaleY > END_SCALE) {

                                    if (previousDistance != Float.MIN_VALUE) {

                                        scaleView(v, localDistance, scaleY)
                                        moveView(v, localDistance, moveY)

                                    }

                                    previousDistance = localDistance

                                }

                            }

                        }

                    }

                }

            }
        }

        return true
    }

    private fun scaleView(v: View, distance: Float, mScale: Float) {

        fun calculateScale(distance: Float): Float {

            if (distance < 0) {

                return v.scaleY - (distance - previousDistance) * mScale

            } else {

                return v.scaleY + (distance - previousDistance) * mScale

            }


        }

        v.scaleY = calculateScale(distance)

    }

    private fun moveView(v: View, distance: Float, mMoveScale: Float) {

        if (distance < 0) {

            v.translationY -= (distance - previousDistance) * mMoveScale

        } else {

            v.translationY += (distance - previousDistance) * mMoveScale

        }


    }

    private fun isScrollGesture(event: MotionEvent, ptrIndex: Int, originalY: Float): Boolean {

        val moveY = Math.abs(event.getY(ptrIndex) - originalY)

        return moveY > viewScaledTouchSlop

    }

    private fun distance(event: MotionEvent, first: Int, second: Int): Float {

        if (event.pointerCount >= 2) {

            return event.getY(first) - event.getY(second)

        } else {

            return 0f

        }
    }

    fun convertPixelsToDp(context: Context, px: Float): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

}
