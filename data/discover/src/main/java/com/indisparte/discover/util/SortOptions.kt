package com.indisparte.discover.util

import androidx.annotation.StringRes
import com.indisparte.discover.R


/**
 * Sealed class representing different sorting options for a list.
 *
 * @param sortName The string resource ID representing the name of the sorting option.
 * @param id The unique ID for this sorting option.
 */
sealed class SortOptions(
    @StringRes val sortName: Int,
    val id: Int,
) {

    /**
     * The order value associated with this sorting option.
     */
    abstract val order: String

    /**
     * Represents an ascending popularity sorting option.
     */
    object AscendingPopularity : SortOptions(R.string.ascending_popularity, 1) {
        override val order: String
            get() = "popularity.asc"
    }

    /**
     * Represents a descending popularity sorting option.
     */
    object DescendingPopularity : SortOptions(R.string.descending_popularity, 2) {
        override val order: String
            get() = "popularity.desc"
    }

    /**
     * Represents a descending vote average sorting option.
     */
    object DescendingVoteAverage : SortOptions(R.string.descending_vote_average, 3) {
        override val order: String
            get() = "vote_average.desc"
    }

    /**
     * Represents an ascending vote average sorting option.
     */
    object AscendingVoteAverage : SortOptions(R.string.ascending_vote_average, 4) {
        override val order: String
            get() = "vote_average.asc"
    }

    /**
     * Returns a string representation of the sorting option.
     *
     * @return A string containing the ID and order of the sorting option.
     */
    override fun toString(): String {
        return "SortOptions(id=$id, order='$order')"
    }
}



