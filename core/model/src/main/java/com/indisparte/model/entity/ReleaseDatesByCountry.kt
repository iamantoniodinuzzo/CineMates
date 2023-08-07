package com.indisparte.model.entity


data class ReleaseDatesByCountry(
    val country: String,
    val releaseDates: List<ReleaseDate>,
)

fun List<ReleaseDatesByCountry>.findReleaseDateByCountry(country: String): List<ReleaseDate>? {
    return find { it.country.equals(country, true) }?.releaseDates
}