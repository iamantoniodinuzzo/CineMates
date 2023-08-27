package com.indisparte.model.entity.tv

/**
 * Represents detailed information about a specific season of a TV show.
 *
 * @property airDate The air date of the season.
 * @property episodes The list of episodes in the season.
 * @property id The unique ID associated with the season.
 * @property seasonId The ID of the season.
 * @property name The name of the season.
 * @property overview An overview of the season.
 * @property posterPath The poster path of the season.
 * @property seasonNumber The season number.
 * @property voteAverage The average vote rating for the season.
 * @author Antonio Di Nuzzo
 */
 class SeasonDetails(
     airDate: String,
     val episodes: List<Episode>,
     id: String,
     val seasonId: Int,
     name: String,
     overview: String,
     posterPath: String,
     seasonNumber: Int,
     voteAverage: Int,
) : Season(
    airDate = airDate,
    episodeCount = episodes.size,
    id = id.toInt(),
    name = name,
    overview = overview,
    posterPath = posterPath,
    seasonNumber = seasonNumber,
    voteAverage = voteAverage
)