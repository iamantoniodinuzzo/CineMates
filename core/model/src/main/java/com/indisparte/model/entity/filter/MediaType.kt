package com.indisparte.model.entity.filter

/**
 * @author Antonio Di Nuzzo
 */
enum class MediaType(
    val value: String
) {
    MOVIE("movie"), TV("tv");

    override fun toString(): String {
        return value
    }
}