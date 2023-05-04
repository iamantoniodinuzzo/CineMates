package com.example.cinemates.domain.model.common

import com.example.cinemates.util.Order
import com.example.cinemates.util.MovieSort
import java.io.Serializable

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SortBy(val sort: MovieSort , val order: Order){
    override fun toString(): String {
        return "$sort.$order"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SortBy) return false

        if (sort != other.sort) return false
        if (order != other.order) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sort.hashCode()
        result = 31 * result + order.hashCode()
        return result
    }
}

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
    val sortBy: SortBy?,
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
   class Builder{
        private var name: String? = null
        private var sortBy: SortBy? = null
        private var genresId: String? = null
        private var castId: String? = null
        private var year: Int? = null

        fun name(name: String?) = apply { this.name = name }
        fun sortBy(movieSort: MovieSort = MovieSort.POPULARITY, order: Order = Order.DESC) =
            apply { this.sortBy = SortBy(movieSort,order)}

        fun genresId(genresId: List<Int>?) = apply {
            this.genresId = genresId?.joinToString(separator = ",")
        }

        fun castId(castId: List<Int>?) =
            apply { this.castId = castId?.joinToString(separator = ",") }

        fun year(year: Int?) = apply { this.year = year }

        /**
         * Builds a new `MovieFilter` instance with the provided criteria.
         *
         * This function creates a new `MovieFilter` instance with the criteria provided by the
         * various functions in the `Builder` class. If the `name` property is not set or
         * is set to null, an exception is thrown with a message indicating that `Name must
         * not be null`.
         *
         * @return A new `MovieFilter` instance with the provided criteria.
         * @throws IllegalArgumentException If the `name` property is not set or is set to null.
         */
        fun build(): MovieFilter {
            requireNotNull(name) { "Name must not be null" }
            return MovieFilter(name!!, sortBy, genresId, castId, year)
        }


    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MovieFilter) return false

        if (name != other.name) return false
        if (sortBy != other.sortBy) return false
        if (genresId != other.genresId) return false
        if (castId != other.castId) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (sortBy?.hashCode() ?: 0)
        result = 31 * result + (genresId?.hashCode() ?: 0)
        result = 31 * result + (castId?.hashCode() ?: 0)
        result = 31 * result + (year ?: 0)
        return result
    }

    override fun toString(): String {
        return "MovieFilter(name='$name', sortBy=$sortBy, genresId=$genresId, castId=$castId, year=$year)"
    }


}
