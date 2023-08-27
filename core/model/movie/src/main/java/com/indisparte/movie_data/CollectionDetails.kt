package com.indisparte.movie_data


/**
 * Represents detailed information about a collection of movies.
 *
 * @property backdropPath The path to the backdrop image of the collection.
 * @property id The unique identifier of the collection.
 * @property name The name of the collection.
 * @property overview A brief overview or description of the collection.
 * @property parts The list of movies that are part of this collection.
 * @property posterPath The path to the poster image of the collection.
 * @author Antonio Di Nuzzo
 */
class CollectionDetails(
    backdropPath: String?,
    id: Int,
    name: String,
    val overview: String?,
    val parts: List<Movie>,
    posterPath: String?,
) : BelongsToCollection(backdropPath, id, name, posterPath)

