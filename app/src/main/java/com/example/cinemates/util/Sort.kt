package com.example.cinemates.util

import androidx.annotation.StringRes
import com.example.cinemates.R

/**
 * Enumeration representing sorting order.
 * @property value The sorting order value ("asc" or "desc").
 * @property nameResId The resource ID of the localized sorting order name.
 * @author Antonio Di Nuzzo
 */
enum class Order(val value: String, @StringRes val nameResId: Int) {
    ASC("asc", R.string.order_asc),
    DESC("desc", R.string.order_desc);

    override fun toString(): String {
        return value
    }
}

/**
 * Enumeration representing sorting options.
 * @property value The sorting option value (e.g. "popularity").
 * @property nameResId The resource ID of the localized sorting option name.
 * @author Antonio Di Nuzzo (Indisparte)
 */
enum class Sort(val value: String, @StringRes val nameResId: Int) {
    POPULARITY("popularity", R.string.sort_popularity),
    RELEASE_DATE("release_date", R.string.sort_release_date),
    REVENUE("revenue", R.string.sort_revenue),
    PRIMARY_RELEASE_DATE("primary_release_date", R.string.sort_primary_release_date),
    ORIGINAL_TITLE("original_title", R.string.sort_original_title),
    VOTE_AVERAGE("vote_average", R.string.sort_vote_average),
    VOTE_COUNT("vote_count", R.string.sort_vote_count);

    override fun toString(): String {
        return value
    }


}