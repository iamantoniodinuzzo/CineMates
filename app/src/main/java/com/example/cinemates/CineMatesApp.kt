package com.example.cinemates

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 21/04/2022 at 15:28
 */
@HiltAndroidApp
class CineMatesApp : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: CineMatesApp? = null

        /**
         * Returns the application context.
         */
        fun getContext(): Context {
            return instance!!.applicationContext
        }
    }
}