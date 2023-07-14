package com.indisparte.genre.mapper

import com.indisparte.model.entity.Genre
import com.indisparte.response.GenreDTO


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun GenreDTO.mapToGenre(): Genre {
    return Genre(id = this.id, name = this.name)
}