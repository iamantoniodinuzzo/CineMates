package com.example.cinemates.data.remote.response.tvShow


import com.example.cinemates.data.remote.response.credits.CastDTO

class GuestStarDTO(
    adult: Boolean,
    character: String,
    creditId: String,
    gender: Int?,
    id: Int,
    knownForDepartment: String,
    name: String,
    order: Int,
    originalName: String,
    popularity: Double,
    profilePath: String?
) : CastDTO(
    adult,
    character,
    creditId,
    gender,
    id,
    knownForDepartment,
    name,
    order,
    originalName,
    popularity,
    profilePath
)