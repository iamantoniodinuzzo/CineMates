package com.example.cinemates.domain.model.common

import com.example.cinemates.util.Order
import com.example.cinemates.util.TvSort
import com.example.cinemates.util.TvType

/**
 * A class representing a TV show filter.
 *
 * This class provides a convenient way to create filters with various criteria, including
 * sorting, genres, runtime, and episodeGroupType. The `name` property is a mandatory property that
 * must be provided in the constructor, while the other properties are optional and
 * can be set using the `Builder` class.
 *
 * @property name The name of the filter (mandatory).
 * @property sortBy The sorting criteria for the filter (optional).
 * @property genresId The genres for the filter (optional).
 * @property runtime The runtime for the filter (optional).
 * @property type The type of TV shows to include in the filter (optional).
 * @author Antonio Di Nuzzo (Indisparte)
 */
class TvFilter private constructor(
    val name: String,
    val sortBy: String?,
    val genresId: String?,
    val runtime: Int?,
    val type: String?
):java.io.Serializable {
    /**
     * A builder class for creating new TV show filter instances.
     *
     * This class provides a convenient way to create new `TvFilter` instances with various
     * criteria. The `name` property is a mandatory property that must be set using the
     * `name` function. The other properties are optional and can be set using the various
     * functions provided by the `Builder` class.
     *
     * Example usage:
     *
     * ```kotlin
     * val filter = TvFilter.Builder()
     *     .name("Popular TV Shows")
     *     .sortBy(TvSort.POPULARITY_DESC)
     *     .genresId(listOf(18, 35))
     *     .runtime(30)
     *     .episodeGroupType(EpisodeGroupType.SERIES)
     *     .build()
     * ```
     *
     * @property name The name of the filter (mandatory).
     * @property sortBy The sorting criteria for the filter (optional).
     * @property genresId The genres for the filter (optional).
     * @property runtime The runtime for the filter (optional).
     * @property type The type of TV shows to include in the filter (optional).
     */
    class Builder {
        private var name: String? = null
        private var sortBy: String? = null
        private var genresId: String? = null
        private var runtime: Int? = null
        private var type: String? = null

        fun name(name: String) = apply { this.name = name }

        fun sortBy(sort: TvSort? = TvSort.POPULARITY, order: Order? = Order.DESC) =
            apply { this.sortBy = "$sort.$order" }

        fun genresId(genresId: List<Int>?) = apply { this.genresId = genresId?.joinToString(separator = ",") }
        fun runtime(runtime: Int?) = apply { this.runtime = runtime }
        fun type(type: TvType?) = apply { this.type = type?.id.toString() }

        /**
         * Builds a new `TvFilter` instance with the provided criteria.
         *
         * This function creates a new `TvFilter` instance with the criteria provided by the
         * various functions in the `Builder` class. If the `name` property is not set or
         * is set to null, an exception is thrown with a message indicating that `Name must
         * not be null`.
         *
         * @return A new `TvFilter` instance with the provided criteria.
         * @throws IllegalArgumentException If the `name` property is not set or is set to null.
         */
        fun build(): TvFilter {
            requireNotNull(name) { "Name must not be null" }
            return TvFilter(name!!, sortBy, genresId, runtime, type)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TvFilter) return false

        if (name != other.name) return false
        if (sortBy != other.sortBy) return false
        if (genresId != other.genresId) return false
        if (runtime != other.runtime) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (sortBy?.hashCode() ?: 0)
        result = 31 * result + (genresId?.hashCode() ?: 0)
        result = 31 * result + (runtime ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "TvFilter(name='$name', sortBy=$sortBy, genresId=$genresId, runtime=$runtime, type=$type)"
    }


}