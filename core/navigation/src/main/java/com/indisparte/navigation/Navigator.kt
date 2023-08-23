package com.indisparte.navigation

import androidx.navigation.NavController


/**
 * @author Antonio Di Nuzzo
 */
class Navigator {
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        is NavigationFlow.HomeFlow -> navController.navigate(NavGraphDirections.actionGlobalHomeFlow())
        is NavigationFlow.MovieDetailsFlow -> navController.navigate(
            NavGraphDirections.actionGlobalMovieDetails(
                navigationFlow.msg?.toInt() ?: 0
            )
        )
        is NavigationFlow.SearchFlow -> navController.navigate(NavGraphDirections.actionGlobalSearchFlow())
        is NavigationFlow.DiscoverFlow -> navController.navigate(NavGraphDirections.actionGlobalDiscoverFlow())
        is NavigationFlow.PersonDetailsFlow -> navController.navigate(
            NavGraphDirections.actionPersonDetailsDetails(
                navigationFlow.msg?.toInt() ?: 0
            )
        )
    }
}