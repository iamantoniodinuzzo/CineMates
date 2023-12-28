package com.indisparte.base

import java.io.Serializable
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Enumeration representing the types of media, including movies, TV shows, and episodes.
 *
 * @param id The unique identifier for each media type.
 */
enum class MediaType(val id: Int) {
    MOVIE_TV(0), MOVIE(1), TV(2), EPISODE(3);

    companion object {
        /**
         * Retrieves a [MediaType] based on its unique identifier.
         *
         * @param id The unique identifier of the media type.
         * @return The corresponding [MediaType] enum value.
         * @throws NoSuchElementException If no media type with the specified id is found.
         */
        @Throws(NoSuchElementException::class)
        fun fromId(id: Int): MediaType {
            return values().find { it.id == id }
                ?: throw NoSuchElementException("There is no media type with this id: $id")
        }
    }
}

/**
 * Represents a media item with common properties like ID, name, popularity, poster path, vote average, media type,
 * and additional properties related to user preferences.
 *
 * @param id The unique identifier for the media item.
 * @param mediaName The name or title of the media item.
 * @param popularity The popularity score of the media item, or null if not available.
 * @param posterPath The relative path to the poster image, or null if not available.
 * @param voteAverage The average vote rating for the media item.
 * @param mediaType The type of media (e.g., movie, TV show).
 * @param isFavorite Indicates whether the media item is marked as a favorite.
 * @param isToSee Indicates whether the user intends to watch the media item.
 * @param isSeen Indicates whether the user has already seen the media item.
 *
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
    private var _isToSee: Boolean = false,
    private var _isSeen: Boolean = false,
) : TMDBItem(), Serializable {

    /**
     * Gets or sets the "to see" status of the media item.
     *
     * When [isToSee] is set to true, [isSeen] is automatically set to false.
     */
    var isToSee: Boolean
        get() = _isToSee
        set(value) {
            // Quando isToSee viene impostato su true, imposta isSeen su false
            _isToSee = value
            if (value) {
                _isSeen = false
            }
        }

    /**
     * Gets or sets the "seen" status of the media item.
     *
     * When [isSeen] is set to true, [isToSee] is automatically set to false.
     */
    var isSeen: Boolean
        get() = _isSeen
        set(value) {
            // Quando isSeen viene impostato su true, imposta isToSee su false
            _isSeen = value
            if (value) {
                _isToSee = false
            }
        }

    /**
     * Gets the vote average rounded to one decimal place as a string.
     *
     * @return The formatted vote average string, or an empty string if the vote average is zero.
     */
    val voteAverageRounded: String
        get() = if (voteAverage == 0.0) {
            ""
        } else {
            ((voteAverage * 10).roundToInt() / 10.0).toString()
        }

    /**
     * Gets the complete poster image path for the media item with a width of 780 pixels.
     *
     * @return The complete image path, or null if the relative path is empty.
     */
    val completePosterPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, posterPath)

    /**
     * Gets the complete poster image path for the media item with a width of 500 pixels.
     *
     * @return The complete image path, or null if the relative path is empty.
     */
    val completePosterPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, posterPath)

    /**
     * Gets the vote average as a string.
     *
     * @return The string representation of the vote average.
     */
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

    override fun toString(): String {
        return "Media(id=$id, mediaName='$mediaName', popularity=$popularity, voteAverage=$voteAverage, isFavorite=$isFavorite, isToSee=$isToSee, isSeen=$isSeen)"
    }


}
