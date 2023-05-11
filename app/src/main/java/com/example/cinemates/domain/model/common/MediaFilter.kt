package com.example.cinemates.domain.model.common

import com.example.cinemates.util.MediaSortOption
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.Order
import java.io.Serializable


class MediaFilter private constructor(
    val mediaType: MediaType,
    val name: String?,
    val sortBy: SortBy?,
    val genresId: String?,
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
     * val filter = MediaFilter.Builder()
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
     * @property year The release year for the filter (optional).
     */
    class Builder {
        private var mediaType: MediaType = MediaType.MOVIE
        private var name: String? = null
        private var sortBy: SortBy? = null
        private var genresId: String? = null
        private var year: Int? = null

        fun name(name: String?) = apply { this.name = name }
        fun mediaType(mediaType: MediaType) = apply { this.mediaType = mediaType }
        fun sortBy(
            mediaSort: MediaSortOption? = MediaSortOption.Popular,
            order: Order? = Order.DESC
        ) =
            apply { this.sortBy = SortBy(mediaSort, order) }

        fun genresId(genresId: MutableSet<Int>) = apply {
            this.genresId = genresId.joinToString(separator = ",")
                .replace("[", "")
                .replace("]", "")
        }

        fun year(year: Int?) = apply { this.year = year }

        /**
         * Builds a new `MediaFilter` instance with the provided criteria.
         *
         * This function creates a new `MediaFilter` instance with the criteria provided by the
         * various functions in the `Builder` class. If the `name` property is not set or
         * is set to null, an exception is thrown with a message indicating that `Name must
         * not be null`.
         *
         * @return A new `MediaFilter` instance with the provided criteria.
         * @throws IllegalArgumentException If the `name` property is not set or is set to null.
         */
        fun build(): MediaFilter {
            return MediaFilter(mediaType, name, sortBy, genresId, year)
        }


    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MediaFilter) return false

        if (name != other.name) return false
        if (sortBy != other.sortBy) return false
        if (genresId != other.genresId) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (sortBy?.hashCode() ?: 0)
        result = 31 * result + (genresId?.hashCode() ?: 0)
        result = 31 * result + (year ?: 0)
        return result
    }

    override fun toString(): String {
        return "MediaFilter(mediaType=${mediaType},name='$name', sortBy=$sortBy, genresId=$genresId,  year=$year)"
    }


}



