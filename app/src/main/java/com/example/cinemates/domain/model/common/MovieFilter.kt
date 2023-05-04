package com.example.cinemates.domain.model.common

import com.example.cinemates.util.Order
import com.example.cinemates.util.MovieSort
import java.io.Serializable

/**
 * A class representing a filter used to search for movies.
 *
 * This class provides a convenient way to create filters with various criteria, including
 * sorting, genres, cast, and release year. The `name` property is a mandatory property
 * that must be provided in the constructor, while the other properties are optional and
 * can be set using the `Builder` class.
 *
 * @property name The name of the filter (mandatory).
 * @property sortBy The sorting criteria for the filter (optional). Default value 'popularity.desc'
 * @property genresId The genres for the filter (optional).
 * @property castId The cast members for the filter (optional).
 * @property year The release year for the filter (optional).
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieFilter private constructor(
    val name: String,
    val sortBy: String?,
    val genresId: String?,
    val castId: String?,
    val year: Int?
) : Serializable {

    /**
     * A builder class for creating new filter instances.
     *
     * This class provides a convenient way to create new filter instances with various
     * criteria. The `name` property is a mandatory property that must be set using the
     * `name` function. The other properties are optional and can be set using the various
     * functions provided by the `Builder` class.
     *
     * Example usage:
     *
     * ```kotlin
     * val filter = MovieFilter.Builder()
     *     .name("Action movies")
     *     .sortBy(MovieSort.POPULARITY, Order.DESC)
     *     .genresId(listOf(28, 12, 80))
     *     .castId(listOf(123, 456))
     *     .year(2021)
     *     .build()
     * ```
     *
     * @property name The name of the filter (mandatory).
     * @property sortBy The sorting criteria for the filter (optional).
     * @property genresId The genres for the filter (optional).
     * @property castId The cast members for the filter (optional).
     * @property year The release year for the filter (optional).
     */
    data class Builder(
        var name: String? = null,
        var sortBy: String? = null,
        var genresId: String? = null,
        var castId: String? = null,
        var year: Int? = null
    ) {
        fun name(name: String?) = apply { this.name = name }
        fun sortBy(movieSort: MovieSort? = MovieSort.POPULARITY, order: Order? = Order.DESC) =
            apply { this.sortBy = "$movieSort.$order" }

        fun genresId(genresId: List<Int>?) = apply {
            this.genresId = genresId?.joinToString(separator = ",")
        }

        fun castId(castId: List<Int>?) =
            apply { this.castId = castId?.joinToString(separator = ",") }

        fun year(year: Int?) = apply { this.year = year }
        fun build(): MovieFilter {
            requireNotNull(name) { "Name must not be null" }
            return MovieFilter(name!!, sortBy, genresId, castId, year)
        }
    }
}
