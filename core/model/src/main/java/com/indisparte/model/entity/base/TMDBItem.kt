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

    protected fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60

        if (hours == 0 && minutes == 0) return ""

        return "$hours h $minutes min"
    }

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


    protected fun getCompleteImagePath(baseUrl: String, urlToImage: String?): String? {
        return if (urlToImage.isNullOrEmpty()) null else "$baseUrl$urlToImage"
    }
}