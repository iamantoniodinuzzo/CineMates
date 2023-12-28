package com.indisparte.movie_data

import androidx.annotation.StringRes
import com.indisparte.base.TMDBItem
import com.indisparte.movie.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Represents the different types of release for a media item.
 *
 * @property type An integer representing the release type.
 * @property releaseStringRes The resource ID of the string representing the release type.
 * @author Antonio Di Nuzzo
 */
enum class ReleaseType(val type: Int, @StringRes val releaseStringRes: Int) {
    PREMIERE(1, R.string.release_premiere),
    THEATRICAL_LIMITED(2, R.string.release_theatrical_limited),
    THEATRICAL(3, R.string.release_theatrical),
    DIGITAL(4, R.string.release_digital),
    PHYSICAL(5, R.string.release_physical),
    TV(6, R.string.release_tv);

    companion object {
        @Throws(NoSuchElementException::class)
        fun fromValue(value: Int): ReleaseType {
            return ReleaseType.values().find { it.type == value }
                ?: throw NoSuchElementException("There is no Release type with this value: $value")
        }
    }
}

/**
 * Represents release date information for a media item.
 *
 * @property certification The certification associated with the release.
 * @property releaseDate The release date in "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" format (UTC).
 * @property type The type of release.
 */
data class ReleaseDate(
    val certification: String,
    private val releaseDate: String,
    private val type: Int,
) : TMDBItem() {

    /**
     * The release type based on the type value.
     */
    val releaseType: ReleaseType
        get() = ReleaseType.fromValue(type)

    /**
     * The formatted release date in the device's local format.
     */
    val formattedReleaseDate: String?
        get() {
            return formatDate(releaseDate, TMDB_RELEASE_DATE_FORMAT_UTC)
        }
}

/**
 * Extension function to retrieve the certification of the latest release date in the list.
 */
fun List<ReleaseDate>.getLatestReleaseCertification(): String {
    val latestRelease = this.maxByOrNull { it.formattedReleaseDate?:"" }

    return latestRelease?.certification ?: ""
}


