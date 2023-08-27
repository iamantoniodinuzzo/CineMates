package com.indisparte.actor.mapper

import com.indisparte.actor.response.MovieCreditResponseDTO

fun MovieCreditResponseDTO.mapToMovieCredits(): List<com.indisparte.movie_data.MovieCredit> {
    val movieCredits = mutableListOf<com.indisparte.movie_data.MovieCredit>()

    cast.forEach { castItem ->
        val character = castItem.character
        val id = castItem.id
        val posterPath = castItem.posterPath
        val releaseDate = castItem.releaseDate
        val title = castItem.title
        val voteAverage = castItem.voteAverage
        val popularity = castItem.popularity
        val adult = castItem.adult

        movieCredits.add(
            com.indisparte.movie_data.MovieCredit(
                character = character,
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                department = null, // department not in cast,
                popularity = popularity,
                adult = adult

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
        val popularity = crewItem.popularity
        val adult = crewItem.adult

        movieCredits.add(
            com.indisparte.movie_data.MovieCredit(
                character = null, // Character not in crew
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                department = department,
                popularity = popularity,
                adult = adult
            )
        )
    }

    return movieCredits
}





