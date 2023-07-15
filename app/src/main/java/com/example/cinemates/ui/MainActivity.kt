package com.example.cinemates.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cinemates.R
import com.example.cinemates.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


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
}