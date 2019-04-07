package com.setname.weather.mvp.utils.gestures.pinch

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

class PinchToCloseCurrentCity(val viewConfig: ViewConfiguration) {

    private var mPtrCount = 0

    private var mPrimStartTouchEventY = -1f
    private var mSecStartTouchEventY = -1f

    private var mPrimSecStartTouchDistance = 0f
    private var mViewScaledTouchSlop = 0
    private var mPrevDistance = 0f

    private val mEndDistancePX = 200f
    private val mEndSizePX: Float = 400f

    private var mScaleY = 0f
    private var mMoveY = 0f

    private val maxScale: Float = mEndDistancePX / 1920f

    init {
        mViewScaledTouchSlop = viewConfig.scaledTouchSlop
    }

    fun onTouch(v: View, event: MotionEvent): Boolean {

        val action = event.action and MotionEvent.ACTION_MASK

        when (action) {

            MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_DOWN -> {

                mPtrCount++
                if (mPtrCount == 2) {

                    mPrevDistance = Float.MIN_VALUE
                    mSecStartTouchEventY = event.getY(1)
                    mPrimSecStartTouchDistance = distance(event, 0, 1)

                        if (mPrimSecStartTouchDistance > 0) {

                            mScaleY = 1 / (mPrimSecStartTouchDistance - mEndSizePX)
                            mMoveY = ((960 - (mEndDistancePX /*- mEndSizePX*/)) / mPrimSecStartTouchDistance) - 0.01f

                        } else {

                            mScaleY = 1 / -(mPrimSecStartTouchDistance + mEndSizePX)
                            mMoveY = ((960 - (mEndDistancePX /*- mEndSizePX*/)) / -mPrimSecStartTouchDistance) - 0.01f

                        }

                }
            }

            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                mPtrCount--
                if (mPtrCount < 2) {
                    mSecStartTouchEventY = -1f
                }
                if (mPtrCount < 1) {
                    mPrimStartTouchEventY = -1f
                }
            }

            MotionEvent.ACTION_MOVE -> {

                val isPrimMoving = isScrollGesture(event, 0, mPrimStartTouchEventY)
                val isSecMoving = mPtrCount > 1 && isScrollGesture(event, 1, mSecStartTouchEventY)

                if (isSecMoving) {

                    if (isPrimMoving && isSecMoving) {

                        if (Math.abs(mPrimSecStartTouchDistance)>1720) {

                            val localDistance = distance(event, 0, 1)

                            if (mPrevDistance != Float.MIN_VALUE) {

                                if (v.scaleY > maxScale) {

                                    scaleView(v, localDistance, mScaleY)

                                }

                                moveView(v, localDistance, mMoveY)


                            }

                            mPrevDistance = localDistance

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

                return v.scaleY - (distance - mPrevDistance) * mScale

            } else {

                return v.scaleY + (distance - mPrevDistance) * mScale

            }


        }

        v.scaleY = calculateScale(distance)


    }

    private fun moveView(v: View, distance: Float, mMoveScale: Float) {


        if (distance < 0) {

            v.translationY -= (distance - mPrevDistance) * mMoveScale

        } else {

            v.translationY += (distance - mPrevDistance) * mMoveScale

        }


    }

    private fun isScrollGesture(event: MotionEvent, ptrIndex: Int, originalY: Float): Boolean {

        val moveY = Math.abs(event.getY(ptrIndex) - originalY)

        return moveY > mViewScaledTouchSlop

    }

    private fun distance(event: MotionEvent, first: Int, second: Int): Float {

        if (event.pointerCount >= 2) {

            return event.getY(first) - event.getY(second)

        } else {

            return 0f

        }
    }

}