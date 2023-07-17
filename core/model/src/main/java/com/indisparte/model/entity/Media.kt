package com.indisparte.model.entity

import kotlin.math.roundToInt


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Media(
    val id: Int,
    val mediaName: String,
    val popularity: Double,
    private val posterPath: String?,
    private val voteAverage: Double,
) {
    companion object {
        protected const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"
        protected const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"
    }

    val voteAverageRounded: String
        get() = ((voteAverage * 10).roundToInt() / 10.0).toString()

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
}
