package com.setname.weather.mvp.utils.gestures.pinch

import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration


class PinchToCloseCurrentCity(val viewConfig: ViewConfiguration) {

    private enum class Finger(val number: Int){
        PRIMARY(0),
        SECOND(1);
    }

    private val SCREEN_HEIGTH = 1920f;

    /**
     * Number of fingers at screen at the same time
     */
    private var ptrCount = 0

    /**
     * Start y coordinate for primary and second fingers
     */
    private var primaryStartTouchEventY = -1f
    private var secondStartTouchEventY = -1f

    /**
     * The first distance
     * Initialized as soon as the second finger move down
     */
    private var primaryToSecondStartTouchDistance = 0f

    private var viewScaledTouchSlop = 0

    /**
     * The previous distance between primary and second fingers
     */
    private var previousDistance = 0f

    /**
     * The finish y coordinate position after folding
     */
    private val END_Y = 400f

    /**
     * The finish view size in PX after folding
     */
    private val END_SIZE: Float = 200f

    /**
     * Like END_SIZE but in scale format
     */
    private val END_SCALE: Float = END_SIZE / SCREEN_HEIGTH

    /**
     * Min distance between fingers
     */
    private val PRIMARY_TO_SECOND_DISTANCE = 200f

    /**
     * Upper zone for finger
     */
    private val UP_ZONE = 100f

    /**
     * Lower zone for finger
     */
    private val BOTTOM_ZONE = 1820f

    /**
     * UP_ZONE and BOTTOM_ZONE are the zones for fingers there listener would register fingers
     */

    /**
     * Is the zone there interactions would happend
     */
    private val INTERACTION_ZONE = BOTTOM_ZONE - UP_ZONE


    private val INTERACTION_ZONE_FOR_PINCH = BOTTOM_ZONE - UP_ZONE - PRIMARY_TO_SECOND_DISTANCE

    /**
     * The var using for scale calculating
     */
    private var scaleY = 0f

    /**
     * The var using for move calculating
     */
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
                    secondStartTouchEventY = event.getY(Finger.SECOND.number)
                    primaryToSecondStartTouchDistance = distance(event, Finger.PRIMARY.number, Finger.SECOND.number)

                    if (primaryToSecondStartTouchDistance > 0) {

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

                val localDistance = distance(event, Finger.PRIMARY.number, Finger.SECOND.number)

                val isPrimMoving = isScrollGesture(event, Finger.PRIMARY.number, primaryStartTouchEventY)
                val isSecMoving = ptrCount > 1 && isScrollGesture(event, Finger.SECOND.number, secondStartTouchEventY)

                if (isSecMoving) {

                    if (isPrimMoving && isSecMoving) {

                        if (Math.abs(primaryToSecondStartTouchDistance) > INTERACTION_ZONE) {

                            if (Math.abs(localDistance) < INTERACTION_ZONE) {

                                if (Math.abs(localDistance) > PRIMARY_TO_SECOND_DISTANCE) {

                                    if (previousDistance != Float.MIN_VALUE) {

                                        scaleView(v, localDistance, scaleY)
                                        moveView(v, localDistance, moveY)

                                    }

                                }

                                previousDistance = localDistance

                            }

                        } else if (v.scaleY < 1) {

                            if (Math.abs(localDistance) < INTERACTION_ZONE) {

                                if (previousDistance != Float.MIN_VALUE) {

                                    val calcScale = calculateScale(v, localDistance, scaleY)

                                    if (calcScale > END_SCALE) {

                                        v.scaleY = calcScale
                                        moveView(v, localDistance, moveY)

                                    }

                                }

                            }

                            previousDistance = localDistance

                        }

                    }

                }

            }
        }

        return true
    }

    private fun calculateScale(v: View, distance: Float, mScale: Float): Float {

        if (distance < 0) {

            return v.scaleY - (distance - previousDistance) * mScale

        } else {

            return v.scaleY + (distance - previousDistance) * mScale

        }


    }

    private fun scaleView(v: View, distance: Float, mScale: Float) {

        v.scaleY = calculateScale(v, distance, mScale)

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

}
