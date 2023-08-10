package com.indisparte.discover.util

/**
 * Enum class representing various sorting options for media items.
 *
 * @property sortType The string representation of the sorting option.
 *
 * @constructor Creates a new instance of [SortOptions] with the provided sort type.
 * @author Antonio Di Nuzzo
 */
enum class SortOptions(val sortType: String) {
    /**
     * Sort media items by popularity in descending order.
     */
    POPULARITY_DESC("popularity.desc"),

    /**
     * Sort media items by popularity in ascending order.
     */
    POPULARITY_ASC("popularity.asc"),

    /**
     * Sort media items by vote average in ascending order.
     */
    VOTE_AVERAGE_ASC("vote_average.asc"),

    /**
     * Sort media items by vote average in descending order.
     */
    VOTE_AVERAGE_DESC("vote_average.desc")
}
