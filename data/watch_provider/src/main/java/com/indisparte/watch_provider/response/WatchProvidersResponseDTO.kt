package com.indisparte.watch_provider.response


data class WatchProvidersResponseDTO(
    val id: Int,
    val results: Map<String, CountryResultDTO>,
) {
    fun getCountryResultByCountry(country: String): List<CountryResultDTO> {
        return results
            .filter { it.key.contains(country, ignoreCase = true) }
            .map { it.value }    }
}