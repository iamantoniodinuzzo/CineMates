package com.example.cinemates.domain.model

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
