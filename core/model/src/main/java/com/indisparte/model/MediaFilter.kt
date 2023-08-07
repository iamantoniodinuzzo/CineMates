package com.indisparte.model

import java.io.Serializable


data class MediaFilter(
    val mediaType: MediaType = MediaType.MOVIE,
    val name: String? = null,
    val sortBy: SortBy?,
    private val genresId: List<String>? = null,
    val year: Int? = null,
) : Serializable {

    val genresIdAsString: String?
        get() {
            return genresId?.joinToString(separator = ",")
                ?.replace("[", "")
                ?.replace("]", "")
        }

}



