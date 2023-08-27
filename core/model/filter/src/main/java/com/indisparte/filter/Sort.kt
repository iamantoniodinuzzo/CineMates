package com.indisparte.filter

import androidx.annotation.StringRes
import com.indisparte.ui.R

/**
 * Enumeration representing sorting order.
 *
 * @property value The sorting order value ("asc" or "desc").
 * @property nameResId The resource ID of the localized sorting order name.
 * @constructor Creates a sorting order enumeration value with the given parameters.
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
 * Sealed class representing media sorting options.
 *
 * @property value The sorting option value.
 * @property nameResId The resource ID of the localized sorting option name.
 * @constructor Creates a media sorting option sealed class with the given parameters.
 */
sealed class MediaSortOption(val value: String, @StringRes val nameResId: Int) {
    object Popular : MediaSortOption("popularity", R.string.sort_popularity)

    object ReleaseDate : MediaSortOption("release_date", R.string.sort_release_date)

    override fun toString(): String {
        return value
    }

    // Add more shared sorting options between media types here
}

/**
 * Sealed class representing movie-specific sorting options.
 *
 * @constructor Creates a movie-specific sorting option sealed class with the given parameters.
 */
sealed class MovieSortOption(value: String, nameResId: Int) : MediaSortOption(value, nameResId) {
    object Revenue : MovieSortOption("revenue", R.string.sort_revenue)

    object VoteAverage : MovieSortOption("vote_average", R.string.sort_vote_average)

    object VoteCount : MovieSortOption("vote_count", R.string.sort_vote_count)

    // Add more movie-specific sorting options here
}

/**
 * Sealed class representing TV-specific sorting options.
 *
 * @constructor Creates a TV-specific sorting option sealed class with the given parameters.
 */
sealed class TvSortOption(value: String, nameResId: Int) : MediaSortOption(value, nameResId) {
    object FirstAirDate : TvSortOption("first_air_date", R.string.sort_first_air_date)

    // Add more TV-specific sorting options here
}