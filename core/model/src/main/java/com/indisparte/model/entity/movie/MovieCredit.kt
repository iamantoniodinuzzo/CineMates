package com.indisparte.model.entity.movie

/**
 * @author Antonio Di Nuzzo
 */
class MovieCredit(
    adult: Boolean,
    private val character: String?,
    id: Int,
    popularity: Double,
    posterPath: String?,
    releaseDate: String?,
    title: String,
    voteAverage: Double,
    private val department: String?,
) : Movie(
    adult,
    id,
    popularity,
    posterPath,
    releaseDate,
    title,
    voteAverage
) {
    val credit: String
        get() = character ?: department.orEmpty()

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