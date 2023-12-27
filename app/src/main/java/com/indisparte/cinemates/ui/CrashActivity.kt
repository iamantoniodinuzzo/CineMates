package com.indisparte.cinemates.ui

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.setContentView
import com.indisparte.cinemates.databinding.ActivityCrashBinding
import timber.log.Timber


/**
 *@author Antonio Di Nuzzo
 */
class CrashActivity : AppCompatActivity() {
    private val TAG = CrashActivity::class.java.simpleName

    private var _binding: ActivityCrashBinding? = null
    private val binding: ActivityCrashBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val errorDetails = intent.getStringExtra("errorDetails")
        Timber.tag(TAG).d("onCreate: $errorDetails")

        if (errorDetails != null) {
            checkAppIsDevelopment(errorDetails)
        }

        binding.btRestartApp.setOnClickListener {
            restartApp()
        }
    }

    private fun restartApp() {
        val packageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent?.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }

    private fun checkAppIsDevelopment(errorDetails: String) {
        // this logic is written for the to check the app is in debug mode or release mode
        val isInDebugApp = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        if (isInDebugApp) {
            binding.tvErrorLogs.isVisible = true
            binding.tvErrorLogs.text = errorDetails
        }
    }

    override fun onBackPressed() {
        // nothing we will do on back pressed
    }
}