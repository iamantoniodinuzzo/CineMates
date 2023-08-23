package com.indisparte.discover.util

import androidx.annotation.StringRes
import com.indisparte.discover.R

/**
 * Enum class representing various sorting options for media items.
 *
 * @property sortName The string resource ID representing the localized name of the sorting option.
 * @property ascendingOrder The string representation of the ascending sorting option.
 * @property descendingOrder The string representation of the descending sorting option.
 *
 * @constructor Creates a new instance of [SortOptions] with the provided parameters.
 * @author Antonio Di Nuzzo
 */
enum class SortOptions(
    @StringRes val sortName: Int,
    val ascendingOrder: String,
    val descendingOrder: String,
    val id:Int
) {
    /**
     * Sort media items by popularity.
     */
    POPULARITY(R.string.popularity, "popularity.asc", "popularity.desc", 1),

    /**
     * Sort media items by vote average in ascending order.
     */
    VOTE_AVERAGE(R.string.vote_average, "vote_average.asc", "vote_average.desc", 2),
}


