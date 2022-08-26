package com.example.cinemates.view.ui


import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.setupWithNavController
import com.example.cinemates.R
import com.example.cinemates.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding private set
    private lateinit var navController: NavController

    private val topLevelDestinations: Set<Int>
        get() = setOf(R.id.homeFragment,
            R.id.discoverFragment,
            R.id.savedFragment,
            R.id.profileFragment)

    private lateinit var slideIn: Animation
    private lateinit var slideOut: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadAnimations()
        setupNavigation()
    }

    private fun loadAnimations() {
        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out)
    }

    private fun setupNavigation() = binding.bottomNavigationView.run {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHost
        navController = navHost.navController
        setupWithNavController(navController)
        lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener { _, navDestination, _ ->
                when (navDestination.id) {
                    // Showing bottom navigation in top level destinations
                    in topLevelDestinations -> if (visibility != View.VISIBLE) {
                        startAnimation(slideIn)
                        visibility = View.VISIBLE
                    }
                    // Hiding bottom navigation in non top level destinations
                    else -> {
                        startAnimation(slideOut)
                        visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}