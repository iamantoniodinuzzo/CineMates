package com.example.cinemates.domain.model.tv

import com.example.cinemates.domain.model.credits.Person

class CreatedBy(
    val creditId: String,
    val gender: String,
    id: Int,
    name: String,
    profilePath: String
) : Person(
    id,
    name,
    profilePath
)
