package com.example.cinemates.util

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat.getSystemService
import com.example.cinemates.CineMatesApp

object VibrationUtils {

    /**
     * Vibrates the phone for the given duration in milliseconds.
     */
    fun vibrate(duration: Long) {
        val vibrator = CineMatesApp.getContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(duration)
        }
    }
}

