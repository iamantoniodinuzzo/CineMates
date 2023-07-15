package com.indisparte.cinemates.domain.model.tv


import com.indisparte.cinemates.domain.model.credits.Cast

class GuestStar(
    character: String,
    id: Int,
    name: String,
    profilePath: String,
) : Cast(
    character,
    id,
    name,
    profilePath
)