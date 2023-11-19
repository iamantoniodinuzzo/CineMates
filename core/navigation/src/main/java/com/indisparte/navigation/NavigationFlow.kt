package com.indisparte.navigation

/**
 * @author Antonio Di Nuzzo
 */
sealed class NavigationFlow(val msg: String?) {
    data object HomeFlow : NavigationFlow(null)
    class MovieDetailsFlow(movieId: Int) : NavigationFlow(movieId.toString())
    class PersonDetailsFlow(personId: Int) : NavigationFlow(personId.toString())
    data object SearchFlow : NavigationFlow(null)
    data object DiscoverFlow : NavigationFlow(null)
    data object MediaListCreationFlow:NavigationFlow(null)
}
