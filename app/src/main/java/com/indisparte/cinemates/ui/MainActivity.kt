package com.indisparte.cinemates.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.indisparte.cinemates.R
import com.indisparte.cinemates.databinding.ActivityMainBinding
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.Navigator
import com.indisparte.navigation.ToFlowNavigable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess
import android.os.Process

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToFlowNavigable {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val navigator: Navigator = Navigator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        handleUncaughtException()
        setContentView(binding.root)
        setupNavigation()

    }

    private fun setupNavigation() = binding.bottomNavigationView.run {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        navigator.navController = navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                com.indisparte.home.R.id.homeFragment,
                com.indisparte.media_search.R.id.searchContainerFragment,
                com.indisparte.media_discover.R.id.discoverFragment,
                com.indisparte.saved.R.id.savedFragment
            )
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
                    val isATopDestination =
                        appBarConfiguration.topLevelDestinations.contains(destination.id)
                    isVisible = if (isATopDestination) {
                        if (!isVisible)
                            startAnimation(
                                AnimationUtils.loadAnimation(
                                    this@MainActivity,
                                    R.anim.slide_up
                                )
                            )
                        true
                    } else {
                        if (isVisible)
                            startAnimation(
                                AnimationUtils.loadAnimation(
                                    this@MainActivity,
                                    R.anim.slide_down
                                )
                            )

                        false
                    }
                }
            }
        }

        setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}

fun AppCompatActivity.handleUncaughtException() {
    Thread.setDefaultUncaughtExceptionHandler { _, throwable ->

        /**
        here you can report the throwable exception to Sentry or Crashlytics or whatever crash reporting service you're using,
        otherwise you may set the throwable variable to _ if it'll remain unused
         */


        val errorReport = StringBuilder()
        CoroutineScope(Dispatchers.IO).launch {
            var arr = throwable.stackTrace
            errorReport.append("---------------- Main Crash Name ----------------\n")
            errorReport.append(throwable)
            errorReport.append("\n\n")
            errorReport.append("---------------- Stack Strace ----------------\n\n")
            for (i in arr) {
                errorReport.append(i)
                errorReport.append("\n")
            }
            errorReport.append("\n---------------- end of crash deatils ----------------\n\n")

            /** If the exception was thrown in a background thread inside
            then the actual exception can be found with getCause*/
            errorReport.append("background thread Crash Log ----------------\n")
            val cause: Throwable? = throwable.cause
            if (cause != null) {
                errorReport.append("Main Crash Name - $cause".trimIndent())

                arr = cause.stackTrace
                for (i in arr) {
                    errorReport.append(i)
                    errorReport.append("\n")
                }
            }
            errorReport.append("end of background thread Crash Log ----------------\n\n")
            withContext(Dispatchers.Main) {
                val intent = Intent(this@handleUncaughtException, CrashActivity::class.java).apply {
                    putExtra("errorDetails", errorReport.toString())
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
                finish()
               Process.killProcess(Process.myPid())
                exitProcess(2)
            }

        }


    }
}