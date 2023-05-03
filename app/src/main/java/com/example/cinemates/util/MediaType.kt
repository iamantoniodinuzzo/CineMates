package com.example.cinemates.util

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 10:30
 */
enum class MediaType(
    val value: String
) {
    MOVIE("movie"), TV("tv");

    override fun toString(): String {
        return value
    }
}