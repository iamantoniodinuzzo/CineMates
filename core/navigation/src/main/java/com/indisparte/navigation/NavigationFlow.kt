package com.indisparte.navigation

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class NavigationFlow(val msg: String?) {
    object HomeFlow : NavigationFlow(null)
    class MovieDetailsFlow(movieId: Int) : NavigationFlow(movieId.toString())
    class PersonDetailsFlow(personId: Int) : NavigationFlow(personId.toString())
    object SearchFlow : NavigationFlow(null)
    object DiscoverFlow : NavigationFlow(null)
}
