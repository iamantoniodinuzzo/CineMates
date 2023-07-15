package com.indisparte.model.entity


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
open class Media(
    val id: Int,
    val mediaName: String,
    val popularity: Double,
    private val posterPath: String?,
    val voteAverage: Double,
) {
    companion object {
        protected const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"
        protected const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"
    }

    val completePosterPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W780/$posterPath"

    val completePosterPathW500: String?
        get() = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL_W500/$posterPath"

    val voteAverageAsString: String
        get() = voteAverage.toString()
}
