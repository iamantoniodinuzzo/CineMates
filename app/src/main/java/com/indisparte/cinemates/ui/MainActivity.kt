package com.indisparte.cinemates.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToFlowNavigable {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val navigator: Navigator = Navigator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.discoverFragment
            )
        )

        lifecycleScope.launchWhenResumed {
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

        setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}