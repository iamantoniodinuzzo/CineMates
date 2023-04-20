package com.example.cinemates.domain.model

class Crew(
    id: Int,
    val job: String,
    name: String,
    profilePath: String
) : Person(
    id,
    name,
    profilePath
)