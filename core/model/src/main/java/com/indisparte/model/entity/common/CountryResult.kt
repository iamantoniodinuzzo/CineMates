package com.indisparte.model.entity.common


data class CountryResult(
    val link: String,
    val flatrate: List<WatchProvider>? = null,
    val rent: List<WatchProvider>? = null,
    val buy: List<WatchProvider>? = null,
    val free: List<WatchProvider>? = null,
) {
    val totalDistinctWatchProviders: Int by lazy {
        val watchProvidersSet: MutableSet<WatchProvider> = mutableSetOf()

        flatrate?.let { watchProvidersSet.addAll(it) }
        rent?.let { watchProvidersSet.addAll(it) }
        buy?.let { watchProvidersSet.addAll(it) }
        free?.let { watchProvidersSet.addAll(it) }

        watchProvidersSet.size
    }

    val allWatchProviders: List<WatchProvider> by lazy {
        val watchProviders: MutableSet<WatchProvider> = mutableSetOf()
        flatrate?.let { watchProviders.addAll(it) }
        rent?.let { watchProviders.addAll(it) }
        buy?.let { watchProviders.addAll(it) }
        free?.let { watchProviders.addAll(it) }

        watchProviders.sortedBy { it.displayPriority }
    }

}