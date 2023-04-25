package com.example.cinemates.domain.model


open class Cast(
    val character: String,
    id: Int,
    name: String,
    profilePath: String
) : Person(
    id,
    name,
    profilePath
)