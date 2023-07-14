package com.indisparte.model.entity


data class CountryResult(
    val link: String,
    val flatrate: List<WatchProvider>? = null,
    val rent: List<WatchProvider>? = null,
    val buy: List<WatchProvider>? = null,
    val free: List<WatchProvider>? = null,
)