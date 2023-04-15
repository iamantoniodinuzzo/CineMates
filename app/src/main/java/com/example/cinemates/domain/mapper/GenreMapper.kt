package com.example.cinemates.domain.mapper

import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.domain.model.Genre


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GenreMapper:Mapper<GenreDTO, Genre> {
    override fun map(input: GenreDTO): Genre {
        return Genre(
            id = input.id,
            name =  input.name
        )
    }

}