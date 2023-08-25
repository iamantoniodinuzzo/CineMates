package com.indisparte.model.entity


data class PersonAsCrew(
    val creditId: String,
    val department: String,
    val id: Int,
    val job: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)