package com.indisparte.common

import com.indisparte.base.TMDBItem


/**
 * Represents a watch provider that offers media content for streaming, renting, or buying.
 *
 * @property displayPriority The priority of the watch provider for display.
 * @property logoPath The path to the logo of the watch provider.
 * @property providerName The name of the watch provider.
 * @author Antonio Di Nuzzo
 */
data class WatchProvider(
    val displayPriority: Int,
    private val logoPath: String?,
    val providerName: String,
) : TMDBItem() {
    /**
     * Gets the complete logo path for the watch provider with the specified width of 780 pixels.
     *
     * @return The complete URL of the watch provider's logo with width 780 pixels if the logo path
     * is available, or null otherwise.
     */
    val completeLogoPathW780: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W780, logoPath)

    /**
     * Gets the complete logo path for the watch provider with the specified width of 500 pixels.
     *
     * @return The complete URL of the watch provider's logo with width 500 pixels if the logo path
     * is available, or null otherwise.
     */
    val completeLogoPathW500: String?
        get() = getCompleteImagePath(IMAGE_BASE_URL_W500, logoPath)
}
