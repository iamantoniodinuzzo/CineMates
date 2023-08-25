package com.indisparte.actor.mapper

import com.indisparte.actor.response.MovieCreditResponseDTO
import com.indisparte.actor.response.PersonAsCastDTO
import com.indisparte.actor.response.PersonAsCrewDTO
import com.indisparte.model.entity.MovieCredit
import com.indisparte.model.entity.PersonAsCast
import com.indisparte.model.entity.PersonAsCrew

fun PersonAsCastDTO.mapToPersonAsCast(): PersonAsCast {
    return PersonAsCast(
        character = this.character,
        episodeCount = this.episodeCount,
        firstAirDate = this.firstAirDate,
        id = this.id,
        order = this.order,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun PersonAsCrewDTO.mapToPersonAsCrew(): PersonAsCrew {
    return PersonAsCrew(
        creditId = this.creditId,
        department = this.department,
        id = this.id,
        job = this.job,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage
    )
}

fun MovieCreditResponseDTO.mapToMovieCredit(): MovieCredit {
    return MovieCredit(
        cast = cast.map { it.mapToPersonAsCast() },
        crew = crew.map { it.mapToPersonAsCrew() },
        personId = id
    )
}





