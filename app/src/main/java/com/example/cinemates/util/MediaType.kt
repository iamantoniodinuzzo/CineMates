package com.example.cinemates.util

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 10:30
 */
enum class MediaType(private val value: String) {
    ALL("all"), MOVIE("movie"), TV("tv"), PERSON("person");

    override fun toString(): String {
        return value
    }
}