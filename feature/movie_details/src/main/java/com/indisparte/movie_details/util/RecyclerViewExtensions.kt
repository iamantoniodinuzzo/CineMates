package com.indisparte.movie_details.util

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * Enable a RecyclerView to be used within a ViewPager2 by disabling ViewPager2 from intercepting touch events of the RecyclerView.
 * @Author Antonio Di Nuzzo
 */
internal fun RecyclerView.enableInnerScrollViewPager() {
    addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val action = e.actionMasked
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    // Disallow ViewPager2 to intercept touch events of RecyclerView
                    rv.parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            return false
        }
    })
}
