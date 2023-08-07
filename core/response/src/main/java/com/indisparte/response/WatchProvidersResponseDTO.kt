package com.indisparte.response


data class WatchProvidersResponseDTO(
    val id: Int,
    val results: Map<String, CountryResultDTO>,
) {
    fun getCountryResultByCountry(country: String): CountryResultDTO? {
        return results
            .filter { it.key.contains(country, ignoreCase = true) }
            .values
            .firstOrNull()
    }
}