package com.example.cinemates.data.remote.response.tvShow

import com.example.cinemates.data.remote.response.credits.PersonDTO
import com.google.gson.annotations.SerializedName

class CreatedByDTO(
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    id: Int,
    name: String,
    profilePath: String?
) : PersonDTO(
    id, name, profilePath
)
