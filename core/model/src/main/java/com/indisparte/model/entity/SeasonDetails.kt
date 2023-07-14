package com.indisparte.model.entity


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