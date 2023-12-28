package com.indisparte.movie_data

import com.indisparte.base.TMDBItem


/**
 * Represents a collection to which a media item belongs.
 *
 * @property backdropPath The path to the backdrop image of the collection.
 * @property id The unique identifier of the collection.
 * @property name The name of the collection.
 * @property posterPath The path to the poster image of the collection.
 * @author Antonio Di Nuzzo
 */
open class BelongsToCollection(
    private val backdropPath: String?,
    val id: Int,
    val name: String,
    private val posterPath: String?,
) : TMDBItem() {
    /**
     * Gets the complete poster path for the collection with the specified width of 780 pixels.
     *
     * @return The complete URL of the collection's poster with width 780 pixels if the poster path
     * is available, or null otherwise.
     */
    val completePosterPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, posterPath)

    /**
     * Gets the complete poster path for the collection with the specified width of 500 pixels.
     *
     * @return The complete URL of the collection's poster with width 500 pixels if the poster path
     * is available, or null otherwise.
     */
    val completePosterPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, posterPath)

    /**
     * Gets the complete backdrop path for the collection with the specified width of 780 pixels.
     *
     * @return The complete URL of the collection's backdrop with width 780 pixels if the backdrop path
     * is available, or null otherwise.
     */
    val completeBackdropPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, backdropPath)

    /**
     * Gets the complete backdrop path for the collection with the specified width of 500 pixels.
     *
     * @return The complete URL of the collection's backdrop with width 500 pixels if the backdrop path
     * is available, or null otherwise.
     */
    val completeBackdropPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, backdropPath)
}
