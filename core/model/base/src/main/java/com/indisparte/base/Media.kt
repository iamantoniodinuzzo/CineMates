package com.indisparte.base

import com.indisparte.base.Constants.IMAGE_BASE_URL_W500
import com.indisparte.base.Constants.IMAGE_BASE_URL_W780
import java.io.Serializable
import kotlin.math.roundToInt

enum class MediaType(val id: Int) {
    MOVIE_TV(0), MOVIE(1), TV(2), EPISODE(3);

    companion object {
        @Throws(NoSuchElementException::class)
        fun fromId(id: Int): MediaType {
            return values().find { it.id == id }
                ?: throw NoSuchElementException("There is no media type with this id: $id")
        }
    }
}

/**
 * Represents a media item with properties such as id, name, popularity, etc.
 * @property id The unique identifier of the media item.
 * @property mediaName The name/title of the media item.
 * @property popularity The popularity of the media item.
 * @property completePosterPathW500 The complete path to the poster image of the media item.
 * @property completePosterPathW780 The complete path to the poster image of the media item.
 * @property voteAverageAsString The average vote/rating of the media item in string format
 * @property voteAverageRounded The average vote/rating rounded of the media item
 * @author Antonio Di Nuzzo
 */
open class Media(
    val id: Int,
    val mediaName: String,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double,
    val mediaType: MediaType,
    var isFavorite: Boolean = false,
) : TMDBItem(), Serializable {

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
