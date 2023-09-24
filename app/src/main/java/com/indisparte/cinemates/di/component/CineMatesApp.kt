package com.indisparte.cinemates.di.component

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author Antonio Di Nuzzo
 */
@HiltAndroidApp
class CineMatesApp : Application(){
    override fun onCreate() {

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                //.penaltyDeath() //TODO
                .build()
        )

        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}