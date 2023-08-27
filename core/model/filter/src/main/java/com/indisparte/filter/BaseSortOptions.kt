package com.indisparte.filter

import androidx.annotation.StringRes


/**
 * Sealed class representing different sorting options for a list.
 *
 * @param sortName The string resource ID representing the name of the sorting option.
 * @param id The unique ID for this sorting option.
 */
sealed class BaseSortOptions(
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
    object AscendingPopularity : BaseSortOptions(R.string.ascending_popularity, 1) {
        override val order: String
            get() = "popularity.asc"
    }

    /**
     * Represents a descending popularity sorting option.
     */
    object DescendingPopularity : BaseSortOptions(R.string.descending_popularity, 2) {
        override val order: String
            get() = "popularity.desc"
    }

    /**
     * Represents a descending vote average sorting option.
     */
    object DescendingVoteAverage : BaseSortOptions(R.string.descending_vote_average, 3) {
        override val order: String
            get() = "vote_average.desc"
    }

    /**
     * Represents an ascending vote average sorting option.
     */
    object AscendingVoteAverage : BaseSortOptions(R.string.ascending_vote_average, 4) {
        override val order: String
            get() = "vote_average.asc"
    }

    object DescendingRevenue : BaseSortOptions(R.string.descending_revenue, 5) {
        override val order: String
            get() = "revenue.desc"

    }

    object AscendingRevenue : BaseSortOptions(R.string.descending_revenue, 6) {
        override val order: String
            get() = "revenue.asc"

    }

    object DescendingVoteCount : BaseSortOptions(R.string.descending_vote_count, 7) {
        override val order: String
            get() = "vote_count.desc"

    }

    object AscendingVoteCount : BaseSortOptions(R.string.ascending_vote_count, 8) {
        override val order: String
            get() = "vote_count.asc"

    }

    /**
     * Returns a string representation of the sorting option.
     *
     * @return A string containing the ID and order of the sorting option.
     */
    override fun toString(): String {
        return "BaseSortOptions(id=$id, order='$order')"
    }
}



