package com.indisparte.movie.response


data class CountryResultDTO(
    val link: String,
    val flatrate: List<WatchProviderDTO>? = null,
    val rent: List<WatchProviderDTO>? = null,
    val buy: List<WatchProviderDTO>? = null,
    val free: List<WatchProviderDTO>? = null,
)