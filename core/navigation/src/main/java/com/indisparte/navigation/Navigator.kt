package com.indisparte.navigation

import androidx.navigation.NavController


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class Navigator {
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.HomeFlow -> navController.navigate(NavGraphDirections.actionGlobalHomeFlow())
        is NavigationFlow.MovieDetailsFlow -> navController.navigate(NavGraphDirections.actionGlobalMovieDetails(navigationFlow.msg?.toInt()?:0))
    }
}