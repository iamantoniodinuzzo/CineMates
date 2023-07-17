package com.indisparte.cinemates.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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
        /* val appBarConfiguration = AppBarConfiguration(
             setOf(
                 R.id.homeFragment,
                 R.id.discoverFragment,
                 R.id.savedFragment,
                 R.id.profileFragment
             )
         )*/

        /*lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
                isVisible =
                    appBarConfiguration.topLevelDestinations.contains(destination.id)
            }
        }*/

        setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}