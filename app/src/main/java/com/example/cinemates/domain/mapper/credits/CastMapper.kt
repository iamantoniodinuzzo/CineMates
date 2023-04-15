package com.example.cinemates.domain.mapper.credits

import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.Cast


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class CastMapper:Mapper<CastDTO, Cast> {
    override fun map(input: CastDTO): Cast {
        return Cast(
            id = input.id,
            name = input.name,
            profilePath = input.profilePath.toString(),
            castId = input.castId,
            character = input.character,
            creditId = input.creditId,
        )
    }

}