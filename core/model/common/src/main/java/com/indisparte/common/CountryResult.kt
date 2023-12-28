package com.indisparte.common

/**
 * Represents watch providers available for different types of content in a specific country.
 *
 * @property link The link to the watch provider information.
 * @property flatrate List of watch providers offering content for flatrate streaming.
 * @property rent List of watch providers offering content for rent.
 * @property buy List of watch providers offering content for purchase.
 * @property free List of watch providers offering free content.
 * @author Antonio Di Nuzzo
 */
data class CountryResult(
    val link: String,
    val flatrate: List<WatchProvider>? = null,
    val rent: List<WatchProvider>? = null,
    val buy: List<WatchProvider>? = null,
    val free: List<WatchProvider>? = null,
) {

    /**
     * The list of all watch providers across all categories, sorted by display priority.
     */
    val allWatchProviders: List<WatchProvider> by lazy {
        val watchProviders: MutableSet<WatchProvider> = mutableSetOf()
        flatrate?.let { watchProviders.addAll(it) }
        rent?.let { watchProviders.addAll(it) }
        buy?.let { watchProviders.addAll(it) }
        free?.let { watchProviders.addAll(it) }

        watchProviders.sortedBy { it.displayPriority }
    }

}