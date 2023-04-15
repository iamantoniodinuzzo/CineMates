package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Media
import com.example.cinemates.util.MediaType


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CastToMediaMapper : Mapper<PersonDTO, Media> {
    override fun map(input: PersonDTO): Media {
        return Media(
            mediaType = MediaType.PERSON,
            id = input.id,
            title = input.name,
            posterPath = input.profilePath,
            backdropPath = null,
            voteAverage = 0.0
        )
    }

}