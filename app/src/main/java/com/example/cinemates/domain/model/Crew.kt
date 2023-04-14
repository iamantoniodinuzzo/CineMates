package com.example.cinemates.domain.model

data class Crew(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val creditId: String,
    val job: String,
)