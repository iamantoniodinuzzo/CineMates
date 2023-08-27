package com.indisparte.movie_data


/**
 * Represents the release dates of a media item by country.
 *
 * @property country The country code or name.
 * @property releaseDates The list of release dates for the specified country.
 * @author Antonio Di Nuzzo
 */
data class ReleaseDatesByCountry(
    val country: String,
    val releaseDates: List<ReleaseDate>,
)

/**
 * Finds and returns the list of release dates for the specified country.
 *
 * @param country The country code or name to search for.
 * @return The list of release dates for the specified country, or null if not found.
 * @author Antonio Di Nuzzo
 */
fun List<ReleaseDatesByCountry>.findReleaseDateByCountry(country: String): List<ReleaseDate>? {
    return find { it.country.equals(country, true) }?.releaseDates
}
