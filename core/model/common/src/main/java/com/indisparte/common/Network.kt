package com.indisparte.common

/**
 * Represents a network that broadcasts or streams media content.
 *
 * @property id The unique identifier of the network.
 * @property logoPath The relative path to the network's logo.
 * @property name The name of the network.
 * @property originCountry The origin country of the network.
 * @author Antonio Di Nuzzo
 */
data class Network(
    val id: Int,
    private val logoPath: String,
    val name: String,
    val originCountry: String,
) : com.indisparte.base.TMDBItem() {
    /**
     * Gets the complete URL for the network's logo image with a width of 780 pixels.
     *
     * @return The complete URL for the network's logo image with a width of 780 pixels, or null
     * if the [logoPath] is empty.
     */
    val formattedLogoPathW780: String?
        get() = getCompleteImagePath(
            IMAGE_BASE_URL_W780,
            logoPath
        )

    /**
     * Gets the complete URL for the network's logo image with a width of 500 pixels.
     *
     * @return The complete URL for the network's logo image with a width of 500 pixels, or null
     * if the [logoPath] is empty.
     */
    val formattedLogoPathW500: String?
        get() = getCompleteImagePath(
            IMAGE_BASE_URL_W500,
            logoPath
        )
}