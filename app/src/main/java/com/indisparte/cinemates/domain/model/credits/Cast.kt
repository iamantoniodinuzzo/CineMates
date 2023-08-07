package com.indisparte.cinemates.domain.model.credits


open class Cast(
    val character: String,
    id: Int,
    name: String,
    profilePath: String,
) : Person(
    id,
    name,
    profilePath
)