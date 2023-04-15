package com.example.cinemates.domain.mapper.movie

import com.example.cinemates.data.remote.response.movie.CollectionDTO
import com.example.cinemates.domain.mapper.Mapper

import com.example.cinemates.domain.model.Collection
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CollectionMapper
@Inject
constructor(
    private val movieToMediaMapper: MovieToMediaMapper
): Mapper<CollectionDTO, Collection> {
    override fun map(input: CollectionDTO): Collection {
        return Collection(
            backdropPath = input.backdropPath,
            id = input.id,
            name = input.name,
            parts = input.parts.map { movieToMediaMapper.map(it) },
            posterPath = input.posterPath
        )
    }

}