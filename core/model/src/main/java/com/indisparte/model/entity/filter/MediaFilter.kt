package com.indisparte.model.entity.filter

import java.io.Serializable

/**
 * Represents a filter criteria object for querying media content (movies or TV shows).
 *
 * @param mediaType The type of media content (e.g., movie or TV show).
 * @param name The name of the media content.
 * @param sortBy The sorting option for media content.
 * @param genresId A list of genre IDs associated with the media content.
 * @param year The year of the media content's release.
 */
data class MediaFilter(
    val mediaType: MediaType = MediaType.MOVIE,
    val name: String? = null,
    val sortBy: SortBy?,
    private val genresId: List<String>? = null,
    val year: Int? = null,
) : Serializable {

    /**
     * Gets a comma-separated string of genre IDs from the list of genre IDs.
     *
     * @return Comma-separated genre IDs as a string.
     */
    val genresIdAsString: String?
        get() {
            return genresId?.joinToString(separator = ",")
                ?.replace("[", "")
                ?.replace("]", "")
        }

}



