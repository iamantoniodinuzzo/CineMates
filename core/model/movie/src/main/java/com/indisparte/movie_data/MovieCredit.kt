package com.indisparte.movie_data

/**
 * Represents a movie credit, which includes details about a person's involvement in a movie.
 *
 * @property adult Indicates whether the movie associated with the credit is intended for adult audiences.
 * @property character The character name or role played by the person in the movie.
 * @property id The unique identifier of the movie associated with the credit.
 * @property popularity The popularity rating of the movie associated with the credit.
 * @property posterPath The path to the poster image of the movie associated with the credit.
 * @property releaseDate The release date of the movie associated with the credit.
 * @property title The title of the movie associated with the credit.
 * @property voteAverage The average vote rating of the movie associated with the credit.
 * @property department The department or area of involvement in the movie.
 * @author Antonio Di Nuzzo
 */
class MovieCredit(
    adult: Boolean,
    val character: String?,
    id: Int,
    popularity: Double,
    posterPath: String?,
    releaseDate: String?,
    title: String,
    voteAverage: Double,
    val department: String?,
) : Movie(
    adult,
    id,
    popularity,
    posterPath,
    releaseDate,
    title,
    voteAverage
) {
    /**
     * Gets the credit description, which can be either the character name or the department.
     */
    val credit: String
        get() = character ?: department.orEmpty()

    /**
     * Gets the year of release for the movie associated with the credit.
     */
    val year: String?
        get() {
            return if (!releaseDate.isNullOrEmpty()) {
                val dateComponents = releaseDate.split("-")
                if (dateComponents.size == 3) {
                    dateComponents[0]
                } else {
                    null
                }
            } else {
                null
            }
        }
}
