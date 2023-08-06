package com.indisparte.model.entity

import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt


/**
 * @author Antonio Di Nuzzo
 */
open class Media(
    val id: Int,
    val mediaName: String,
    val popularity: Double?,
    private val posterPath: String?,
    private val voteAverage: Double,
) {
    companion object {
        private const val TMDB_DATE_TIME_FORMAT = "yyyy-MM-dd"
        private const val OUTPUT_DATE_TIME_FORMAT = "dd MMMM yyyy"
    }

    val voteAverageRounded: String
        get() = if (voteAverage == 0.0) {
            ""
        } else {
            ((voteAverage * 10).roundToInt() / 10.0).toString()
        }

    val completePosterPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W780$posterPath"

    val completePosterPathW500: String?
        get() = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W500$posterPath"

    val voteAverageAsString: String
        get() = voteAverage.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Media) return false

        return id == other.id && mediaName == other.mediaName
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + mediaName.hashCode()
        return result
    }

    protected fun getCompleteImagePath(urlToImage: String?): String? {
        return if (urlToImage.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W780$urlToImage"
    }

    protected fun formatDate(inputDate: String): String? {
        if (inputDate.isEmpty()) {
            return null
        }

        val inputFormat = SimpleDateFormat(TMDB_DATE_TIME_FORMAT, Locale.getDefault())
        val outputFormat = SimpleDateFormat(OUTPUT_DATE_TIME_FORMAT, Locale.getDefault())

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
}
