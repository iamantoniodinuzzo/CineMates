package com.indisparte.model.entity.common

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants


/**
 * Represents a poster image associated with media content.
 *
 * @property aspectRatio The aspect ratio of the poster image.
 * @property filePath The relative path to the poster image.
 * @author Antonio Di Nuzzo
 */
data class Poster(
    val aspectRatio: Double,
    private val filePath: String,
) : TMDBItem() {
    /**
     * Gets the complete URL for the poster image with a width of 780 pixels.
     *
     * @return The complete URL for the poster image with a width of 780 pixels, or null
     * if the [filePath] is empty.
     */
    val formattedFilePathW780: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W780, filePath)

    /**
     * Gets the complete URL for the poster image with a width of 500 pixels.
     *
     * @return The complete URL for the poster image with a width of 500 pixels, or null
     * if the [filePath] is empty.
     */
    val formattedFilePathW500: String?
        get() = getCompleteImagePath(Constants.IMAGE_BASE_URL_W500, filePath)
}
