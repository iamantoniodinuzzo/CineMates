package com.indisparte.model.entity.base

import com.indisparte.model.util.Constants
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Locale


/**
 *@author Antonio Di Nuzzo
 */
abstract class TMDBItem {
    /**
     * Formats a date from an input format to an output format.
     *
     * @param inputDate The date to format.
     * @return The formatted date in the output format, or null if the input is empty or invalid.
     */
    protected fun formatDate(inputDate: String?): String? {
        if (inputDate.isNullOrEmpty()) {
            return null
        }

        val inputFormat = SimpleDateFormat(Constants.TMDB_DATE_TIME_FORMAT, Locale.getDefault())
        val outputFormat = SimpleDateFormat(Constants.OUTPUT_DATE_TIME_FORMAT, Locale.getDefault())

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
        if (amount == 0L)
            return ""

        val currency = Currency.getInstance(Locale.getDefault())
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        formatter.currency = currency

        return when {
            amount >= 1_000_000_000 -> "${amount / 1_000_000_000} mld"
            amount >= 1_000_000 -> "${amount / 1_000_000} mln"
            else -> formatter.format(amount)
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