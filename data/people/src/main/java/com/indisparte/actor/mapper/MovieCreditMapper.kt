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

fun MovieCreditResponseDTO.mapToMovieCredits(): List<MovieCredit> {
    val movieCredits = mutableListOf<MovieCredit>()

    cast.forEach { castItem ->
        val character = castItem.character
        val id = castItem.id
        val posterPath = castItem.posterPath
        val releaseDate = castItem.releaseDate
        val title = castItem.title
        val voteAverage = castItem.voteAverage

        movieCredits.add(
            MovieCredit(
                character = character,
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                department = null // department not in cast
            )
        )
    }

    crew.forEach { crewItem ->
        val id = crewItem.id
        val posterPath = crewItem.posterPath
        val releaseDate = crewItem.releaseDate
        val title = crewItem.title
        val voteAverage = crewItem.voteAverage
        val department = crewItem.department

        movieCredits.add(
            MovieCredit(
                character = null, // Character not in crew
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                department = department
            )
        )
    }

    return movieCredits
}





