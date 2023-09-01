package com.indisparte.tv.mapper

import com.indisparte.common.CountryResult
import com.indisparte.common.WatchProvider
import com.indisparte.response.CountryResultDTO
import com.indisparte.response.WatchProviderDTO

/**
 * @author Antonio Di Nuzzo
 */
fun CountryResultDTO.mapToCountryResult(): CountryResult {
    return CountryResult(
        link = this.link,
        flatrate = this.flatrate?.map { it.mapToWatchProvider() },
        rent = this.rent?.map { it.mapToWatchProvider() },
        buy = this.buy?.map { it.mapToWatchProvider() },
        free = this.free?.map { it.mapToWatchProvider() }
    )
}

fun WatchProviderDTO.mapToWatchProvider(): WatchProvider {
    return WatchProvider(
        displayPriority = this.displayPriority,
        logoPath = this.logoPath,
        providerName = this.providerName
    )
}


