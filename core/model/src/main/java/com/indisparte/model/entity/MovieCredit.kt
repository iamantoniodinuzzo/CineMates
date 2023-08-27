package com.indisparte.model.entity

import com.indisparte.model.util.Constants

data class MovieCredit(
    private val character: String?,
    val id: Int,
    private val posterPath: String?,
    private val releaseDate: String?,
    val title: String?,
    val voteAverage: Double,
    private val department: String?,
){
    val credit: String
        get() = character ?: department.orEmpty()

    val completePosterPathW780: String?
        get() = if (posterPath.isNullOrEmpty()) null else "${Constants.IMAGE_BASE_URL_W780}$posterPath"
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