package com.indisparte.model.entity.movie


class CollectionDetails(
    backdropPath: String?,
    id: Int,
    name: String,
    val overview: String?,
    val parts: List<Movie>,
    posterPath: String?,
) : BelongsToCollection(backdropPath, id, name, posterPath)
