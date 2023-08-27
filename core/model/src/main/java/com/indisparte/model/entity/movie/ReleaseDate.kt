package com.indisparte.model.entity.movie

import androidx.annotation.StringRes
import com.indisparte.model.R
import com.indisparte.model.util.Constants.OUTPUT_DATE_TIME_FORMAT
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
    Premiere(1, R.string.release_premiere),
    TheatricalLimited(2, R.string.release_theatrical_limited),
    Theatrical(3, R.string.release_theatrical),
    Digital(4, R.string.release_digital),
    Physical(5, R.string.release_physical),
    TV(6, R.string.release_tv);

    companion object {
        fun fromValue(value: Int): ReleaseType? =
            ReleaseType.values().firstOrNull { it.type == value }
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
) {
    companion object {
        private const val TMDB_RELEASE_DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }

    /**
     * The release type based on the type value.
     */
    val releaseType: ReleaseType?
        get() = ReleaseType.fromValue(type)

    /**
     * The formatted release date in the device's local format.
     */
    val formattedReleaseDate: String
        get() {
            return convertToLocaleDateString(releaseDate)
        }

    private fun convertToLocaleDateString(inputDateString: String): String {
        try {
            val outputFormat = SimpleDateFormat(OUTPUT_DATE_TIME_FORMAT, Locale.getDefault())

            val inputFormat = SimpleDateFormat(TMDB_RELEASE_DATE_FORMAT_UTC, Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")

            // Parse the input string into a Date object
            val date: Date = inputFormat.parse(inputDateString) ?: return ""

            // Format the date in the device's local format
            return outputFormat.format(date)
        } catch (e: Exception) {
            // In case of errors, return an empty string or an error message
            e.printStackTrace()
            return ""
        }
    }
}

/**
 * Extension function to retrieve the certification of the latest release date in the list.
 */
fun List<ReleaseDate>.getLatestReleaseCertification(): String {
    val latestRelease = this.maxByOrNull { it.formattedReleaseDate }

    return latestRelease?.certification ?: ""
}


