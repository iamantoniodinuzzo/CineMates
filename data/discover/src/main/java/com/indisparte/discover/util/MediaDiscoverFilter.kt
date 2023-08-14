package com.indisparte.discover.util

/**
 * Represents a set of filters for discovering media items.
 *
 * @property sortBy The sorting option for the media items. Default is [SortOptions.POPULARITY].
 * @property voteAverageGTE Filters media items by vote average greater than or equal to the provided value.
 * @property withCast Filters media items by including specific cast members by their IDs.
 * @property withGenres Filters media items by including specific genres by their IDs.
 * @property withRuntimeLTe Filters media items by runtime less than or equal to the provided value.
 * @property castIdsCommaSeparated Returns the IDs of cast members in a comma-separated string format.
 * @property genreIdsCommaSeparated Returns the IDs of genres in a comma-separated string format.
 *
 * @constructor Creates a new instance of [MediaDiscoverFilter] with the provided filter options.
 * @author Antonio Di Nuzzo
 */
data class MediaDiscoverFilter(
    val sortBy: SortOptions? = SortOptions.POPULARITY,
    val voteAverageGTE: Float? = null,
    val withCast: List<String>? = null,
    val withGenres: List<String>? = null,
    val withRuntimeLTe: Long? = null,
) {
    /**
     * Returns the IDs of cast members in a comma-separated string format.
     */
    val castIdsCommaSeparated: String?
        get() {
            return withCast?.joinToString(separator = ",")
        }

    /**
     * Returns the IDs of genres in a comma-separated string format.
     */
    val genreIdsCommaSeparated: String?
        get() {
            return withGenres?.joinToString(separator = ",")
        }
}
