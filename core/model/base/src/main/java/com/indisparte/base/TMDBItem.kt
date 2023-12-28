package com.indisparte.base

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale

/**
 *@author Antonio Di Nuzzo
 */
abstract class TMDBItem {
    companion object {
        /**
         * This constant stores the base URL for retrieving images with a width of 780 pixels from the TMDB (The Movie Database) API.
         */
        const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"

        /**
         * This constant stores the base URL for retrieving images with a width of 500 pixels from the TMDB API.
         */
        const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"

        /**
         *  This constant specifies the date and time format used by the TMDB API, which is "yyyy-MM-dd"
         */
        const val TMDB_DATE_TIME_FORMAT = "yyyy-MM-dd"

        /**
         * This constant specifies an output date and time format used for displaying dates, which is "dd MMMM yyyy"
         */
        const val OUTPUT_DATE_TIME_FORMAT = "dd MMMM yyyy"

        const val TMDB_RELEASE_DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'"
    }

    /**
     * Formats a date from an input format to an output format.
     *
     * @param inputDate The date to format.
     * @return The formatted date in the output format, or null if the input is empty or invalid.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    protected fun formatDate(inputDate: String?, inputFormat: String? = null): String? {
        if (inputDate.isNullOrEmpty()) {
            return null
        }

        val inputFormat =
            DateTimeFormatter.ofPattern(inputFormat ?: TMDB_DATE_TIME_FORMAT)
        val outputFormat = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT)

        return try {
            val date = inputFormat.parse(inputDate)
            date?.let { outputFormat.format(it) }
        } catch (e: ParseException) {
            null
        }
    }

    /**
     * Formats a runtime value in minutes into a human-readable string representation.
     *
     * @param runtime The runtime value in minutes.
     * @return A formatted string indicating the runtime in hours and minutes (e.g., "2 h 30 min").
     */
    protected fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60

        if (hours == 0 && minutes == 0) return ""

        return "$hours h $minutes min"
    }

    /**
     * Formats a currency amount into a human-readable currency string representation.
     *
     * @param amount The currency amount to format.
     * @return A formatted string representing the currency amount (e.g., "$1.5 mln").
     */
    protected fun formatCurrency(amount: Long): String {
        return if (amount >= 1_000_000) {
            val divisor = if (amount >= 1_000_000_000) {
                1_000_000_000
            } else {
                1_000_000
            }

            val value = amount.toDouble() / divisor
            val formattedValue = if (value % 1 == 0.0) {
                value.toInt().toString()
            } else {
                String.format("%.1f", value)
            }

            val unit = if (amount >= 1_000_000_000) {
                "mld"
            } else {
                "mln"
            }

            "$formattedValue $unit"
        } else {
            amount.toString()
        }
    }


    /**
     * Generates the complete image path by combining the base URL and the relative path.
     *
     * @param baseUrl The base URL for images.
     * @param urlToImage The relative path to the image.
     * @return The complete image path, or null if the relative path is empty.
     */
    protected fun getCompleteImagePath(baseUrl: String, urlToImage: String?): String? {
        return if (urlToImage.isNullOrEmpty()) null else "$baseUrl$urlToImage"
    }


}