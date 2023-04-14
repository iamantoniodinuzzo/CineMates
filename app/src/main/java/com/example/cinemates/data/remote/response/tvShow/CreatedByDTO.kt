package com.example.cinemates.data.remote.response.tvShow

import com.example.cinemates.data.remote.response.credits.CastDTO

class CreatedByDTO(
    creditId: String,
    gender: Int,
    id: Int,
    name: String,
    profilePath: String
) : CastDTO(creditId, gender, id, name, profilePath)
