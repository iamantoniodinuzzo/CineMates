package com.indisparte.database.model.mapper

import com.indisparte.common.Genre
import com.indisparte.database.model.GenreEntity

/**
 *@author Antonio Di Nuzzo
 */
class GenreEntityMapper : EntityMapper<Genre, GenreEntity> {
    override fun asEntity(domain: Genre): GenreEntity {
        return GenreEntity(
            id = domain.id,
            name = domain.name,
            isFavorite = domain.isFavorite
        )
    }

    override fun asDomain(entity: GenreEntity): Genre {
        return Genre(
            id = entity.id,
            name = entity.name,
            isFavorite = entity.isFavorite
        )
    }
}