package com.example.cinemates.domain.model


import com.example.cinemates.data.remote.response.credits.CastDTO

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