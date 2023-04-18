package com.example.cinemates.domain.mapper.tv

import com.example.cinemates.data.remote.response.tvShow.CreatedByDTO
import com.example.cinemates.domain.mapper.Mapper
import com.example.cinemates.domain.model.CreatedBy


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
/*class CreatedByMapper : Mapper<CreatedByDTO, CreatedBy> {
    override fun map(input: CreatedByDTO): CreatedBy {
        val gender = if (input.gender == 1) {
            "Female"
        } else {
            "Male"
        }
        return CreatedBy(
            creditId = input.creditId,
            gender = gender,
            id = input.id,
            name = input.name,
            profilePath = input.profilePath
        )
    }

}*/

fun CreatedByDTO.mapToCreatedBy():CreatedBy{
    val gender = if (this.gender == 1) {
        "Female"
    } else {
        "Male"
    }
    return CreatedBy(
        creditId = this.creditId,
        gender = gender,
        id = this.id,
        name = this.name,
        profilePath = this.profilePath
    )
}