package com.indisparte.common

import com.indisparte.base.Constants.IMAGE_BASE_URL_W500
import com.indisparte.base.Constants.IMAGE_BASE_URL_W780
import com.indisparte.base.TMDBItem


/**
 * Represents a production company associated with media content.
 *
 * @property id The unique identifier of the production company.
 * @property logoPath The relative path to the logo of the production company.
 * @property name The name of the production company
 * @author Antonio Di Nuzzo
 */
data class ProductionCompany(
    val id: Int,
    private val logoPath: String?,
    val name: String,
) : TMDBItem() {
    /**
     * Gets the complete URL for the logo of the production company with a width of 780 pixels.
     *
     * @return The complete URL for the logo with a width of 780 pixels, or null
     * if the [logoPath] is empty.
     */
    val completeLogoPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, logoPath)

    /**
     * Gets the complete URL for the logo of the production company with a width of 500 pixels.
     *
     * @return The complete URL for the logo with a width of 500 pixels, or null
     * if the [logoPath] is empty.
     */
    val completeLogoPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, logoPath)
}
