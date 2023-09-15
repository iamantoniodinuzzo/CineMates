package com.indisparte.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

/**
 * @author Antonio Di Nuzzo
 */
private fun buildDeepLink(destination: DeepLinkDestination) =
    NavDeepLinkRequest.Builder
        .fromUri(destination.address.toUri())
        .build()

fun NavController.deepLinkNavigateTo(
    deepLinkDestination: DeepLinkDestination,
    popUpTo: Boolean = false
) {
    val builder = NavOptions.Builder()

    if (popUpTo) {
        builder.setPopUpTo(graph.startDestinationId, true)
    }

    navigate(
        buildDeepLink(deepLinkDestination),
        builder.build()
    )
}

sealed class DeepLinkDestination(val address: String) {
    class MovieDetails(movieId: Int) : DeepLinkDestination("cinemates://movieDetails/exampleArgs?id=${movieId}")
    object Next : DeepLinkDestination("cinemates://next")
}