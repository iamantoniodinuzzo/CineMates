package com.indisparte.util.extension

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun View?.gone() {
    this?.let {
        visibility = View.GONE
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun AppCompatActivity.setStatusBarColor(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        with(window) {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars =
                this@setStatusBarColor.isDarkMode().not()
            ContextCompat.getColor(this@setStatusBarColor, color).let {
                window?.statusBarColor = it
            }
        }
    }
}

fun AppCompatActivity.isDarkMode(): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        Configuration.UI_MODE_NIGHT_UNDEFINED -> true
        else -> false
    }
}




fun EditText.addTextChangeListener(
    afterTextChanged: ((Editable?) -> Unit)? = null,
    beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null,
    onTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null,
) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged?.invoke(s)
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int,
        ) {
            beforeTextChanged?.invoke(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(s, start, before, count)
        }
    })
}

/**
 * Enable a RecyclerView to be used within a ViewPager2 by disabling ViewPager2 from intercepting touch events of the RecyclerView.
 * @Author Antonio Di Nuzzo
 */
fun RecyclerView.enableInnerScrollViewPager() {
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



