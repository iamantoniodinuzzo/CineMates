package com.indisparte.movie_data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.indisparte.base.TMDBItem
import com.indisparte.movie.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.math.abs

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
    val releaseDate: String,
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
@RequiresApi(Build.VERSION_CODES.O)
fun List<ReleaseDate>.getLatestReleaseCertification(): String {
    val formatter = DateTimeFormatter.ofPattern(TMDBItem.TMDB_RELEASE_DATE_FORMAT_UTC)
    val now = LocalDate.now()

    return this.mapNotNull { releaseDate ->
        try {
            val data = LocalDate.parse(releaseDate.releaseDate, formatter)
            val distance = abs(now.toEpochDay() - data.toEpochDay())
            Pair(releaseDate.certification, distance)
        } catch (e: DateTimeParseException) {
            // Gestire il parsing della data non riuscito
            println("Errore durante il parsing della data: ${releaseDate.releaseDate}")
            null
        } catch (e: Exception) {
            // Gestire altre eccezioni
            println("Errore sconosciuto: ${e.message}")
            null
        }
    }.minByOrNull { it.second }?.first ?: ""

}


