package com.indisparte.model.entity.base

import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780
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
) : TMDBItem() {

    val voteAverageRounded: String
        get() = if (voteAverage == 0.0) {
            ""
        } else {
            ((voteAverage * 10).roundToInt() / 10.0).toString()
        }

    val completePosterPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, posterPath)

    val completePosterPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, posterPath)

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


}
