package com.indisparte.model.entity

import androidx.annotation.StringRes
import com.indisparte.model.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

enum class ReleaseType(val type: Int, @StringRes val releaseStringRes: Int) {
    Premiere(1, R.string.release_premiere),
    TheatricalLimited(2, R.string.release_theatrical_limited),
    Theatrical(3, R.string.release_theatrical),
    Digital(4, R.string.release_digital),
    Physical(5, R.string.release_physical),
    TV(6, R.string.release_tv);
}



data class ReleaseDate(
    val certification: String,
    private val releaseDate: String,//yyyy-MM-dd'T'HH:mm:ss.SSS'Z' format
    private val type: Int,
) {
    companion object{
        const val TMDB_RELEASE_DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val OUTPUT_RELEASE_DATE_FORMAT = "MMMM dd, yyyy"
    }
    val releaseType: ReleaseType
        get() {
            return when (type) {
                ReleaseType.Premiere.type -> ReleaseType.Premiere
                ReleaseType.TheatricalLimited.type -> ReleaseType.TheatricalLimited
                ReleaseType.Theatrical.type -> ReleaseType.Theatrical
                ReleaseType.Digital.type -> ReleaseType.Digital
                ReleaseType.Physical.type -> ReleaseType.Physical
                ReleaseType.TV.type -> ReleaseType.TV
                else -> ReleaseType.Premiere // Default value if the type doesn't match any enum
            }
        }

    val formattedReleaseDate: String
        get() {
            return convertToLocaleDateString(releaseDate)
        }

    private fun convertToLocaleDateString(inputDateString: String): String {
        try {
            // Definiamo il formato di data e ora per il formato locale del dispositivo
            val outputFormat = SimpleDateFormat(OUTPUT_RELEASE_DATE_FORMAT, Locale.getDefault())

            // Impostiamo il fuso orario per la data di input (UTC)
            val inputFormat = SimpleDateFormat(TMDB_RELEASE_DATE_FORMAT_UTC, Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")

            // Parsiamo la stringa di input nella data
            val date: Date = inputFormat.parse(inputDateString) ?: return ""

            // Formattiamo la data nel formato locale del dispositivo
            return outputFormat.format(date)
        } catch (e: Exception) {
            // In caso di errori, restituire una stringa vuota o un messaggio di errore
            e.printStackTrace()
            return ""
        }
    }
}

fun List<ReleaseDate>.getLatestReleaseCertification(): String {
    val latestRelease = this.maxByOrNull { it.formattedReleaseDate }

    return latestRelease?.certification ?: ""
}

