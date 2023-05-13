package com.example.cinemates.domain.model.credits

import com.example.cinemates.domain.model.credits.Person

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