package com.indisparte.cinemates.domain.model.credits

class Crew(
    id: Int,
    val job: String,
    name: String,
    profilePath: String,
) : Person(
    id,
    name,
    profilePath
)