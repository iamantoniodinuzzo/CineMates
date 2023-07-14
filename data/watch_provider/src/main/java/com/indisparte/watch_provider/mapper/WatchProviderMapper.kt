package com.indisparte.watch_provider.mapper

import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.WatchProvider
import com.indisparte.watch_provider.response.CountryResultDTO
import com.indisparte.watch_provider.response.WatchProviderDTO

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun com.indisparte.watch_provider.response.CountryResultDTO.mapToCountryResult(): CountryResult {
    return CountryResult(
        link = this.link,
        flatrate = this.flatrate?.map { it.mapToWatchProvider() },
        rent = this.rent?.map { it.mapToWatchProvider() },
        buy = this.buy?.map { it.mapToWatchProvider() },
        free = this.free?.map { it.mapToWatchProvider() }
    )
}

fun com.indisparte.watch_provider.response.WatchProviderDTO.mapToWatchProvider(): WatchProvider {
    return WatchProvider(
        displayPriority = this.displayPriority,
        logoPath = this.logoPath,
        providerName = this.providerName
    )
}


