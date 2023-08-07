package com.indisparte.cinemates.domain.model.tv

import com.indisparte.cinemates.domain.model.credits.Person

class CreatedBy(
    val creditId: String,
    val gender: String,
    id: Int,
    name: String,
    profilePath: String,
) : Person(
    id,
    name,
    profilePath
)
