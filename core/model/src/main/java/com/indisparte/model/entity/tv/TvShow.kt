package com.indisparte.model.entity.tv

import com.indisparte.model.entity.base.Media


open class TvShow(
    id: Int,
    val name: String,
    popularity: Double,
    posterPath: String?,
    voteAverage: Double,
) : Media(
    id = id,
    name,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage
)