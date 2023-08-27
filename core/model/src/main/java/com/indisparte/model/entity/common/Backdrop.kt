package com.indisparte.model.entity.common

import com.indisparte.model.entity.base.TMDBItem
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W500
import com.indisparte.model.util.Constants.IMAGE_BASE_URL_W780


/**
 * Represents a backdrop image associated with media content.
 *
 * @property filePath The relative path to the backdrop image.
 * @property height The height of the backdrop image.
 * @property width The width of the backdrop image.
 * @author Antonio Di Nuzzo
 */
data class Backdrop(
    private val filePath: String,
    val height: Int,
    val width: Int,
) : TMDBItem() {

    /**
     * Gets the complete URL for the backdrop image with a width of 780 pixels.
     *
     * @return The complete URL for the backdrop image with a width of 780 pixels, or null if the
     * [filePath] is empty.
     */
    val completePosterPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, filePath)

    /**
     * Gets the complete URL for the backdrop image with a width of 500 pixels.
     *
     * @return The complete URL for the backdrop image with a width of 500 pixels, or null if the
     * [filePath] is empty.
     */
    val completePosterPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, filePath)

}
