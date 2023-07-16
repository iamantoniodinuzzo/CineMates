package com.indisparte.navigation

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
sealed class NavigationFlow(val msg:String?) {
    object HomeFlow : NavigationFlow(null)
    class MovieDetailsFlow(movieId: Int) : NavigationFlow(movieId.toString())
}
