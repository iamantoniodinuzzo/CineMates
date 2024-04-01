package com.indisparte.genre.mapper

import com.indisparte.base.MediaType
import com.indisparte.common.Genre
import com.indisparte.response.GenreDTO


/**
 * @author Antonio Di Nuzzo
 */
fun GenreDTO.mapToGenre(mediaType: MediaType): Genre {
    return Genre(id = this.id, name = this.name, mediaType = mediaType)
}