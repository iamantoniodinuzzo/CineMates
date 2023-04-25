package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.data.remote.response.movie.CollectionDTO
import com.example.cinemates.domain.model.Collection

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
fun CollectionDTO.mapToCollection(): Collection {
    return Collection(
        backdropPath = this.backdropPath?:"",
        id = this.id,
        name = this.name,
        parts = this.parts?.map { it.mapToMedia() }?: listOf(),
        posterPath = this.posterPath ?: ""
    )

}