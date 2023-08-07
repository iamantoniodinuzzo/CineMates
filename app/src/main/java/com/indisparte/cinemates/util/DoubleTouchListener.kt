package com.indisparte.cinemates.util

import android.view.MotionEvent
import android.view.View
import com.google.android.youtube.player.internal.v


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
abstract class DoubleTouchListener: View.OnTouchListener {
    private var lastClickTime: Long = 0

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            onDoubleClick(view, event)
        }
        lastClickTime = clickTime
        return true
    }

    abstract fun onDoubleClick(v: View, event: MotionEvent)

    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
    }

}