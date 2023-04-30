package com.example.cinemates.domain.model.credits

import com.example.cinemates.domain.model.credits.Person


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