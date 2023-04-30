package com.example.cinemates.domain.model.tv


import com.example.cinemates.domain.model.credits.Cast

class GuestStar(
    character: String,
    id: Int,
    name: String,
    profilePath: String
) : Cast(
    character,
    id,
    name,
    profilePath
)